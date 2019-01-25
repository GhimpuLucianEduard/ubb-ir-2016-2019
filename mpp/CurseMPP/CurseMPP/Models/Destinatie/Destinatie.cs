using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CurseMPP.Models
{
	public class Destinatie : HasId<int>
	{	

		//private static int increment = 0;

		public Destinatie(int id, string nume)
		{
			Id = id;
			this.nume = nume;
		}

		public int Id { get; set; }
		public string nume { get; set; }

		public override string ToString()
		{
			return "Id: " + Id + "  Nume:  " + nume;
		}
	}
}
