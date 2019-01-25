using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace RestWepApiMppCurse.Models
{
	[Table("Curse")]
	public class Cursa
	{
		public static int LocuriDefault = 18;

		#region Propietati Cursa

		[Column("Id")]
		[Key]
		public Int64? Id { get; set; }
		[Column("Destinatie")]
		public int IdDestinatie { get; set; }
		[Column("DataPlecare")]
		public DateTime DataPlecare { get; set; }
		[Column("Plecare")]
		public string LocPlecare { get; set; }
		[Column("NrLocuri")]
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

		#endregion

		public override string ToString()
		{
			return Id.ToString();
		}
	}
}