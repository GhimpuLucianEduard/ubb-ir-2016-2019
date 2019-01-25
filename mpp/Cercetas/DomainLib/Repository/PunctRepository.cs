using System.Collections.Generic;
using System.Linq;
using DomainLib.Model;

namespace DomainLib.Repository
{
	public class PunctRepository
	{
		public Punct Find(int id)
		{
			using (var context = new CercetasiEntities())
			{
				return context.Puncts.Find(id);
			}
		}

		public IEnumerable<Punct> GetAll()
		{
			using (var context = new CercetasiEntities())
			{
				return context.Puncts.ToList();
			}
		}
	}
}