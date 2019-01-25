using CurseMPP.Models;

namespace LabMppCurseWPF.Models
{
	/// <summary>
	/// Clasa client
	/// </summary>
	public class Client : HasId<int>
	{	
		
		#region Propietati Client

		public int Id { get; set; }
		public string Nume { get; set; }
		public string Prenume { get; set; }

		#endregion

		/// <summary>
		/// Constructor client
		/// </summary>
		/// <param name="id">Id client</param>
		/// <param name="nume">Nume client</param>
		/// <param name="prenume">Prenume C</param>
		public Client(int id, string nume, string prenume)
		{
			Id = id;
			this.Nume = nume;
			this.Prenume = prenume;
		}

		#region Overrided Functions

		public override string ToString()
		{
			return "Id: " + Id + " Nume: " + Nume + " Prenume: " + Prenume;
		}

		#endregion

	}
}