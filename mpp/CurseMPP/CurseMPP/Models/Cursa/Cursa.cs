using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CurseMPP.Models
{
	public class Cursa : HasId<int>
	{	
		public static int locuriDefault = 18;

		public Cursa(int id, int destinatie, DateTime data, int nrLocuri)
		{
			Id = id;
			idDestinatie = destinatie;
			dataTimePlecare = data;
			nrLocuriDisponibile = nrLocuri;

		}

		public int Id { get; set; }
		public int idDestinatie { get; set; }
		public DateTime dataTimePlecare { get; set; }
		public int nrLocuriDisponibile { get; set; }


		public override string ToString()
		{
			return "Id: " + Id + " idDestinatie: " + idDestinatie + " data plecare: " + dataTimePlecare +
			       " nr locuri disponibile: " + nrLocuriDisponibile;
		}
	}
}
