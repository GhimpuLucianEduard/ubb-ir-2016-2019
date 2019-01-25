using System;

namespace ClientTestCurse
{	
	public class Cursa
	{
		public static int LocuriDefault = 18;

		#region Propietati Cursa

		public Int64? Id { get; set; }
		public int IdDestinatie { get; set; }
		public DateTime DataPlecare { get; set; }
		public string LocPlecare { get; set; }
		public int NrLocuriDisponibile { get; set; }

		#endregion

		#region Constructori

		public Cursa(Int64 id, int destinatie, DateTime data, int nrLocuri, string locPlecare)
		{
			Id = id;
			IdDestinatie = destinatie;
			DataPlecare = data;
			NrLocuriDisponibile = nrLocuri;
			LocPlecare = locPlecare;

		}

		public Cursa()
		{

		}

		public override string ToString()
		{
			return Id + " " + IdDestinatie + " " + DataPlecare + " " + NrLocuriDisponibile + " " + LocPlecare;
		}

		#endregion
	}
}