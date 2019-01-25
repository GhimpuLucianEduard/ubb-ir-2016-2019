using System;
using System.ComponentModel;
using System.Runtime.CompilerServices;
using CurseMPP.Models;


namespace LabMppCurseWPF.Models
{	
	/// <summary>
	/// Clasa Cursa
	/// </summary>
	[Serializable]
	public class Cursa : HasId<int>
	{	
		/// <summary>
		/// Numarul implicit de locuri disponibile
		/// </summary>
		public static int LocuriDefault = 18;

		#region Propietati Cursa

		public int Id { get; set; }
		public int IdDestinatie { get; set; }
		public DateTime DataPlecare { get; set; }
		public string LocPlecare { get; set; }
		public int NrLocuriDisponibile { get; set; }
		public Destinatie Destinatie { get; set; }

		#endregion

		#region Constructori

		/// <summary>
		/// Constructor Cursa cu cheie straina data ca si id
		/// </summary>
		/// <param name="id">Id cursa</param>
		/// <param name="destinatie">Id destinatie</param>
		/// <param name="data">Data plecarii</param>
		/// <param name="nrLocuri">Nr de locuri disponibile</param>
		/// <param name="locPlecare">Locul plecarii</param>
		public Cursa(int id, int destinatie, DateTime data, int nrLocuri, string locPlecare)
		{
			Id = id;
			IdDestinatie = destinatie;
			DataPlecare = data;
			NrLocuriDisponibile = nrLocuri;
			LocPlecare = locPlecare;

		}

		/// <summary>
		/// Constructor Cursa cu cheie straina data ca si referint la clasa
		/// </summary>
		/// <param name="id">Id cursa</param>
		/// <param name="destinatie">Id destinatie</param>
		/// <param name="data">Data plecarii</param>
		/// <param name="nrLocuri">Nr de locuri disponibile</param>
		/// <param name="locPlecare">Locul plecarii</param>
		public Cursa(int id, Destinatie destinatie, DateTime dataPlecare, int nrLocuriDisponibile,  string locPlecare)
		{
			Id = id;
			IdDestinatie = destinatie.Id;
			DataPlecare = dataPlecare;
			NrLocuriDisponibile = nrLocuriDisponibile;
			Destinatie = destinatie;
			LocPlecare = locPlecare;
		}

		public Cursa()
		{
			
		}

		#endregion

		public override string ToString()
		{
			return Id.ToString();
		}
		
		
	}
}
