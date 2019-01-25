using System;
using System.Collections.Generic;
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
	public class ServerProxy : IServer
	{	
		private string _host;
		private int _port;

		private IObserver _client;
		private NetworkStream _stream;
		private IFormatter _formatter;
		private TcpClient _connection;

		private Queue<IResponse> _responses;
		private bool _finished;
		private EventWaitHandle _waitHandle;

		public ServerProxy(string host, int port)
		{
			_host = host;
			_port = port;
			_responses = new Queue<IResponse>();
		}


		public virtual void Login(Personal personal, IObserver client)
		{
			CreateConnection();
			var personalDto = DTOUtils.GetFromDTO(personal);
			SendRequest(new LoginRequest(personalDto));
			var response = ReadResponse();
			

			if (response is OkResponse)
			{
				this._client = client;
				return;
			}


			if (response is ErrorResponse)
			{
				var error = (ErrorResponse)response;
				throw new AppException(error.ErrorMessage);
			}

		}


		private void CloseConnection()
		{
			_finished = true;
			try
			{
				_stream.Close(1500);
				_connection.Close();
				_waitHandle.Close();
				_client = null;
			}
			catch (Exception e)
			{
				Console.WriteLine(e.StackTrace);
			}
		}

		private IResponse ReadResponse()
		{
			IResponse response = null;
			try
			{
				_waitHandle.WaitOne();
				lock (_responses)
				{
					response = _responses.Dequeue();
				}
			}
			catch (Exception e)
			{
				Console.WriteLine(e);
			}

			return response;
		}

		private void SendRequest(IRequest request)
		{
			try
			{
				lock (_stream)
				{
					_formatter.Serialize(_stream, request);
					_stream.Flush();
				}

			}
			catch (Exception e)
			{
				throw new Exception("Error sending object "+ e);
			}
		}

		private void CreateConnection()
		{
			try
			{
				_connection = new TcpClient(_host, _port);
				_stream = _connection.GetStream();
				_formatter = new BinaryFormatter();
				//_stream.Position = 0;
				_finished = false;
				_waitHandle = new AutoResetEvent(false);
				StartReader();

			}
			catch (Exception e)
			{
				Console.WriteLine(e);
			}
		}

		public void StartReader()
		{
			var thread = new Thread(Run);
			thread.Start();
		}

		public virtual void Run()
		{
			while (!_finished)
			{
				try
				{
					var response = _formatter.Deserialize(_stream);
					Console.WriteLine("Response received: "+ response);
					
					if (response is UpdateResponse)
					{
						lock (response)
						{
							HandleUpdate((UpdateResponse)response);
						}
						
					}
					else
					{
						lock (response)
						{
							_responses.Enqueue((IResponse)response);
						}
				
						_waitHandle.Set();
					}

				}
				catch (Exception e)
				{
					Console.WriteLine(e);
				}
			}
		}

		private void HandleUpdate(UpdateResponse response)
		{
			if (response is NewRezervareResponse)
			{
				try
				{
					_client.newRezervare();
				}
				catch (AppException e)
				{
					Console.WriteLine(e);
				}
			}
		}

		public Cursa[] GetAllCurse(Personal personal)
		{
			CreateConnection();
			SendRequest(new GetCurseRequest(DTOUtils.GetFromDTO(personal)));
			var response = ReadResponse();
			
			if (response is ErrorResponse)
			{
				var err = (ErrorResponse)response;
				throw new AppException(err.ErrorMessage);
			}

			var resp = (GetCurseResponse) response;
			var curseDTO = resp.Curse;
			var curseToSend = DTOUtils.GetFromDTO(curseDTO);
			return curseToSend;
		}

		public Destinatie[] GetAllDestinatii()
		{
			CreateConnection();
			SendRequest(new GetDestinatiiRequest());
			var response = ReadResponse();
			
			if (response is ErrorResponse)
			{
				var err = (ErrorResponse)response;
				throw new AppException(err.ErrorMessage);
			}

			var resp = (GetDestinatiiResponse)response;
			var destinatiiDTO = resp.Destinatii;
			var destToSend = DTOUtils.GetFromDTO(destinatiiDTO);
			return destToSend;
		}

		public Loc[] GetLocuri(int idCursa)
		{
			CreateConnection();
			SendRequest(new GetLocuriRequest(idCursa));
			var response = ReadResponse();
			
			if (response is ErrorResponse)
			{
				var err = (ErrorResponse)response;
				throw new AppException(err.ErrorMessage);
			}

			var resp = (GetLocuriResponse)response;
			var locuriDTO = resp.Locuri;
			var dataToSend = DTOUtils.GetFromDTO(locuriDTO);
			return dataToSend;
		}

		public Cursa FindCursa(DateTime data, Destinatie destinatie)
		{
			CreateConnection();
			SendRequest(new FindCursaRequest(data, DTOUtils.GetFromDTO(destinatie)));
			var response = ReadResponse();
			
			if (response is ErrorResponse)
			{
				var err = (ErrorResponse)response;
				throw new AppException(err.ErrorMessage);
			}

			var resp = (FindCursaResponse)response;
			var cursa = resp.Cursa;
			var dataToSend = DTOUtils.GetFromDTO(cursa);
			return dataToSend;
		}

		public void AdaugaRezervare(int idCursa, string nume, string prenume, int nrLocuri)
		{
			CreateConnection();
			SendRequest(new AddRezervareRequest(new AdaugaRezervareDTO(idCursa,nrLocuri,nume,prenume)));
			var response = ReadResponse();
			

			if (response is OkResponse)
			{
				return;
			}


			if (response is ErrorResponse)
			{
				var err = (ErrorResponse)response;
				throw new AppException(err.ErrorMessage);
			}

		}
	}
}