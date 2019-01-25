using System.Collections.Generic;
using System.Linq;
using DomainLib.Model;

namespace DomainLib.Repository
{
	public class TrecereRepository
	{
		public Trecere Find(int id)
		{
			using (var context = new CercetasiEntities())
			{
				return context.Treceres.Find(id);
			}
		}

		public IEnumerable<Trecere> GetAll()
		{
			using (var context = new CercetasiEntities())
			{
				return context.Treceres.ToList();
			}
		}
	}
}