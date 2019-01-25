using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using CurseMPP.Models;
using LabMppCurseWPF.Models;
using LabMppCurseWPF.Service;
using ServicesInterfaces;

namespace ServerCurseMpp
{
	public class ServerService : MarshalByRefObject, IServer
	{
		private IDictionary<string, IObserver> _clientsDictionary;

		public ServerService()
		{
			_clientsDictionary = new Dictionary<string, IObserver>();
		}

		public void Login(Personal personal, IObserver client)
		{
			var ok = MainService.handleLogin(personal.UserName, personal.Password);
			//Console.WriteLine(client.ToString()+" incearca sa se lo" );
			if (ok)
			{
				if (_clientsDictionary.ContainsKey(personal.UserName))
				{
					throw new Exception("User already logged in!");
				}

				_clientsDictionary[personal.UserName] = client;	
			}
			else
			{
				throw new AppException("Auth failed!");
			}
		}

		public Cursa[] GetAllCurse(Personal personal)
		{
			var curse = MainService.FindAllCurseForView();
			return curse.ToArray();
		}

		public Destinatie[] GetAllDestinatii()
		{
			return MainService.FindAllDestinatii().ToArray();
		}

		public Loc[] GetLocuri(int idCursa)
		{
			return MainService.GenereazaLocuriPentruCursa(idCursa).ToArray();
		}

		public Cursa FindCursa(DateTime data, Destinatie destinatie)
		{
			return MainService.CautaCursaDupaDetalii(data, destinatie);
		}

		public void AdaugaRezervare(int idCursa, string nume, string prenume, int nrLocuri)
		{
			try
			{
				MainService.AdaugaRezervare(idCursa, nume, prenume, nrLocuri);
				
			}
			catch (ServiceException e)
			{
				throw new AppException(e.Message);
			}
			notifyAddRezervare();
		}

		public void notifyAddRezervare()
		{
			foreach (var keyValuePair in _clientsDictionary)
			{
				IObserver client = keyValuePair.Value;
				//Task.Run(() => client.newRezervare());
				//Task task = new Task(client.newRezervare);
				//task.Start();
				client.newRezervare();
			}
		}

		public override object InitializeLifetimeService()
		{
			return null;
		}

	}
}