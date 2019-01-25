using System.Collections.Generic;
using LabMppCurseWPF.Models;
using LabMppCurseWPF.Repository;

namespace LabMppCurseWPF.Service
{	
	/// <summary>
	/// Service care se ocupa de Curse
	/// </summary>
	internal static class ServiceCursa
	{	
		/// <summary>
		/// Repository crud pentru curse
		/// </summary>
		private static IRepository<int,Cursa> _repository = new RepositoryCursa();

		/// <summary>
		/// Cauta si returneaza toate cursele
		/// </summary>
		/// <returns>Lista de curse</returns>
		public static IEnumerable<Cursa> FindAllCurse()
		{
			return _repository.FindAll();
		}
		
		/// <summary>
		/// Cauta si returneaza o cursa din db
		/// </summary>
		/// <param name="id">Id-ul cursei de cuatat</param>
		/// <returns>Cursa, daca exista, null altfel</returns>
		public static Cursa FindOne(int id)
		{
			return _repository.Find(id);
		}

		public static void UpdateCursa(Cursa c)
		{
			_repository.Update(c);
		}
	}
}