using System.Collections.Generic;
using System.Linq;
using Domain.Models;

namespace Domain.Repository
{
	public class JocService
	{
		public Joc Find(int id)
		{
			using (var context = new RazboiEntities())
			{
				return context.Jocs.Find(id);
			}
		}

		public IEnumerable<Joc> GetAll()
		{
			using (var context = new RazboiEntities())
			{
				return context.Jocs.ToList();
			}
		}
	}
}