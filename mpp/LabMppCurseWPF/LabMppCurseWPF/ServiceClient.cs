using System;
using System.Collections.Generic;
using System.ComponentModel;
using CurseMPP.Models;
using LabMppCurseWPF.Models;
using LabMppCurseWPF.ViewModels;
using Networking;
using Networking.DTOs;
using ServicesInterfaces;

namespace LabMppCurseWPF
{
	public class ServiceClient:MarshalByRefObject, IObserver
	{

		private IServer _server;
		private Personal _personal;
		private ViewModelMain _mainVM;

		public ServiceClient(IServer server)
		{
			_server = server;
			_personal = null;
		}

		public bool HandleLogin(string userName, string passwd)
		{
			var personal = new Personal(userName,passwd);
			try
			{
				_server.Login(personal, this);
				Console.WriteLine("Login succeded...");
				_personal = personal;
				return true;
			}
			catch (AppException e)
			{
				return false;
			}

		}

		public List<Cursa> FindAllCurseForView()
		{
			List<Cursa> curseToShow = new List<Cursa>();
			var curse = _server.GetAllCurse(_personal);
			foreach (var cursa in curse)
			{
				curseToShow.Add(cursa);
			}

			return curseToShow;
		}

		public List<Destinatie> FindAllDestinatii()
		{
			List<Destinatie> destToShow = new List<Destinatie>();
			var dest = _server.GetAllDestinatii();
			foreach (var cursa in dest)
			{
				destToShow.Add(cursa);
			}

			return destToShow;
		}

		public List<Loc> GenereazaLocuriPentruCursa(int cursaId)
		{
			List<Loc> dataToShow = new List<Loc>();
			var data = _server.GetLocuri(cursaId);
			foreach (var loc in data)
			{
				dataToShow.Add(loc);
			}

			return dataToShow;
		}

		public Cursa CautaCursaDupaDetalii(DateTime dataCautare, Destinatie destinatieCautare)
		{
			try
			{
				return _server.FindCursa(dataCautare, destinatieCautare);
			}
			catch (AppException e)
			{
				return null;
			}
		}

		public void AdaugaRezervare(int cursaCurenta, string numeAdaugare, string prenumeAdaugare, int numarLocuriAdaugare)
		{
			try
			{
				_server.AdaugaRezervare(cursaCurenta,numeAdaugare,prenumeAdaugare,numarLocuriAdaugare);
				
			}
			catch (AppException e)
			{
				throw e;
			}
		}
			
		public void setViewModel(ViewModelMain mwm)
		{
			_mainVM = mwm;
		}

		public void newRezervare()
		{
			_mainVM.UpdateAction();
		}

	}
}