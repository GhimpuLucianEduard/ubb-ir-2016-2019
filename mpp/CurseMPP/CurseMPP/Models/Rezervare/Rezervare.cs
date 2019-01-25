using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CurseMPP.Models
{
	public class Rezervare : HasId<int>
	{
		public int Id { get; set; }
		public int idClient { get; set; }
		public int idCursa { get; set; }
		public int nrLocuri { get; set; }

		public Rezervare(int id, int idClient, int idCursa, int nrLocuri)
		{
			Id = id;
			this.idClient = idClient;
			this.idCursa = idCursa;
			this.nrLocuri = nrLocuri;
		}

		public override string ToString()
		{
			return "id: " + Id + " idClient: " + idClient + " idCursa: " + idCursa + " nrLocuri: " + nrLocuri;
		}
	}
}
