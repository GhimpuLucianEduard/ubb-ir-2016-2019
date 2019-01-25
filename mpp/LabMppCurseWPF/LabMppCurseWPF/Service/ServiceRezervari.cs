using System.Collections.Generic;
using LabMppCurseWPF.Models;
using LabMppCurseWPF.Repository;

namespace LabMppCurseWPF.Service
{	
	/// <summary>
	/// Service cu operatii de baza pentru Rezervari
	/// </summary>
	internal static class ServiceRezervari
	{	
		/// <summary>
		/// repository cu operatii crud pentru Rezervare
		/// </summary>
		private static IRepository<int, Rezervare> _repository = new RepositoryRezervare();

		public static IEnumerable<Rezervare> FindAll()
		{
			return _repository.FindAll();
		}

		public static void AddRezervare(Rezervare r)
		{
			_repository.Add(r);
		}
		
	}
}