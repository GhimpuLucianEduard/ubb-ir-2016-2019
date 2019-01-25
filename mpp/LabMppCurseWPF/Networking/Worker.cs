using System;
using System.Net.Sockets;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using System.Threading;
using CurseMPP.Models;
using LabMppCurseWPF.Models;
using Networking.DTOs;
using Networking.Requests;
using Networking.Responses;
using ServicesInterfaces;

namespace Networking
{
	public class Worker : IObserver
	{
		private IServer _server;
		private TcpClient _connection;

		private NetworkStream _stream;
		private IFormatter _formatter;
		private bool _connected;

		public Worker(IServer server, TcpClient connection)
		{
			_server = server;
			_connection = connection;
			try
			{
				_stream = _connection.GetStream();
				_formatter = new BinaryFormatter();
				//_stream.Position = 0;
				_connected = true;
			}
			catch (Exception e)
			{
				Console.WriteLine(e.StackTrace);
			}
		}

		public virtual void Run()
		{
			while (_connected)
			{
				try
				{
					//_stream.Position = 0;
					var request = _formatter.Deserialize(_stream);
					var response = HandleRequest((IRequest)request);
					if (response != null)
					{
						SendResponse((IResponse) response);
					}
				}
				catch (Exception e)
				{
					Console.WriteLine(e);
				}

				try
				{
					Thread.Sleep(1000);
				}
				catch (Exception e)
				{
					Console.WriteLine(e.StackTrace);
				}
			}
			try
			{
				_stream.Close();
				_connection.Close();
			}
			catch (Exception e)
			{
				Console.WriteLine("Error " + e);
			}

		}

		private void SendResponse(IResponse response)
		{

			lock (_stream)
			{
				Console.WriteLine("Sending response... "+response);
				_formatter.Serialize(_stream, response);
				_stream.Flush();
			}
		}

		private IResponse HandleRequest(IRequest request)
		{	

			IResponse response = null;
			if (request is LoginRequest)
			{
				Console.WriteLine("Login request...");
				var loginRequest = (LoginRequest) request;
				var personalDTO = loginRequest.PersonalDto;
				var personal = DTOUtils.GetFromDTO(personalDTO);

				try
				{
					lock (_server)
					{
						_server.Login(personal, this);
					}
					return new OkResponse();
				}
				catch (AppException e)
				{
					_connected = false;
					return new ErrorResponse(e.Message);
				}
			}

			if (request is GetCurseRequest)
			{
				Console.WriteLine("Get curse request...");
				var req = (GetCurseRequest) request;
				var pers = req.PersonalDto;
				try
				{
					Cursa[] curse;
					lock (_server)
					{
						curse = _server.GetAllCurse(DTOUtils.GetFromDTO(pers));
					}

					var curseDTO = DTOUtils.GetFromDTO(curse);
					return new GetCurseResponse(curseDTO);
				}
				catch (AppException e)
				{
					return new ErrorResponse(e.Message);
				}
			}

			if (request is GetDestinatiiRequest)
			{
				Console.WriteLine("Get destinatii request...");
				var req = (GetDestinatiiRequest)request;
				try
				{
					Destinatie[] destinatii;
					lock (_server)
					{
						destinatii = _server.GetAllDestinatii();
					}

					var destinatieDTO = DTOUtils.GetFromDTO(destinatii);
					return new GetDestinatiiResponse(destinatieDTO);
				}
				catch (AppException e)
				{
					return new ErrorResponse(e.Message);
				}
			}

			if (request is GetLocuriRequest)
			{
				Console.WriteLine("Get locuri request...");
				var req = (GetLocuriRequest)request;
				try
				{
					Loc[] locuri;
					lock (_server)
					{
						locuri = _server.GetLocuri(req.IdCursa);
					}

					var locuriDTO = DTOUtils.GetFromDTO(locuri);
					return new GetLocuriResponse(locuriDTO);
				}
				catch (AppException e)
				{
					return new ErrorResponse(e.Message);
				}
			}

			if (request is FindCursaRequest)
			{
				Console.WriteLine("Get cursa pt detalii request...");
				var req = (FindCursaRequest)request;
				try
				{
					Cursa cursa = new Cursa();	
					lock (_server)
					{
						cursa = _server.FindCursa(req.Data, DTOUtils.GetFromDTO(req.Destinatie));
					}

					return new FindCursaResponse(new CursaDTO(cursa.Id,cursa.DataPlecare,cursa.LocPlecare,cursa.NrLocuriDisponibile,cursa.IdDestinatie));
				}
				catch (AppException e)
				{
					return new ErrorResponse(e.Message);
				}
			}

			if (request is AddRezervareRequest)
			{
				Console.WriteLine("adauga o rezervare request...");
				var req = (AddRezervareRequest)request;
				try
				{
					lock (_server)
					{
						_server.AdaugaRezervare(req.AdaugaRezervareDto.IdCursa,req.AdaugaRezervareDto.Nume,req.AdaugaRezervareDto.Prenume,req.AdaugaRezervareDto.NrLocuri);
					}

					return new OkResponse();
				}
				catch (AppException e)
				{
					return new ErrorResponse(e.Message);
				}
			}

			return response;
		}

		public void newRezervare()
		{
			try
			{	
				Console.WriteLine("am intrat aici");
				SendResponse(new NewRezervareResponse());
			}
			catch (Exception e)
			{
				Console.WriteLine(e);

			}
		}
	}
}