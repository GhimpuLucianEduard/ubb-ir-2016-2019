using System;
using System.Collections.Generic;
using System.Data;
using CurseMPP.Models;
using LabMppCurseWPF.Models;

namespace ServicesInterfaces
{
	public interface IServer
	{
		void Login(Personal personal, IObserver client);
		Cursa[] GetAllCurse(Personal personal);
		Destinatie[] GetAllDestinatii();
		Loc[] GetLocuri(int idCursa);
		Cursa FindCursa(DateTime data, Destinatie destinatie);
		void AdaugaRezervare(int idCursa, string nume, string prenume, int nrLocuri);
	}
}