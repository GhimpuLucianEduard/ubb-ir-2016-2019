using System.Collections.Generic;
using LabMppCurseWPF.Models;
using LabMppCurseWPF.Repository;

namespace LabMppCurseWPF.Service
{
	/// <summary>
	/// Service cu operatii de baza pentru Destinatie
	/// </summary>
	internal static class ServiceDestinatie
	{
		/// <summary>
		/// Repo cu operatii CRUD pentru service
		/// </summary>
		private static IRepository<int,Destinatie> repository = new RepoORMDestinatie();
		//private static IRepository<int,Destinatie> repository = new MockRepoDestinatie();

		/// <summary>
		/// Functie care cauta si reutrneaza toaza destinatiile din db
		/// </summary>
		/// <returns>Lista cu toate destinatiile</returns>
		public static IEnumerable<Destinatie> FindAllDestinatii()
		{
			return repository.FindAll();
		}
	}
}