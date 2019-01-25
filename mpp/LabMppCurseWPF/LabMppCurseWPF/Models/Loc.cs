namespace CurseMPP.Models
{	
	/// <summary>
	/// Clasa utilizara folosita pentru a afisa in view 
	/// o lista de locuri pentru o anumita rezervare
	/// </summary>
	public class Loc
	{
		#region Propietati
		public int NrCurent { get; set; }
		public string Nume { get; set; }
		public string Prenume { get; set; }
		#endregion

		#region  Contructori

		/// <summary>
		/// Constructor pentru a genera un loc neasignat
		/// </summary>
		/// <param name="nrCurent">Numarul locului</param>
		public Loc(int nrCurent)
		{
			NrCurent = nrCurent;
			Nume = "-";
			Prenume = "-";
		}

		/// <summary>
		/// Constructor pentru a asigna atat locul cat si persona
		/// </summary>
		/// <param name="nrCurent">Numarul locului</param>
		/// <param name="nume">Numele</param>
		/// <param name="prenume">Prenumele</param>
		public Loc(int nrCurent, string nume, string prenume)
		{
			NrCurent = nrCurent;
			Nume = nume;
			Prenume = prenume;
		}

		#endregion

	}
}