using System.Collections.Generic;
using System.Linq;
using Domain.Models;

namespace Domain.Repository
{
	public class JucatorRepository
	{
		public Jucator Find(int id)
		{
			using (var context = new RazboiEntities())
			{
				return context.Jucators.Find(id);
			}
		}

		public IEnumerable<Jucator> GetAll()
		{
			using (var context = new RazboiEntities())
			{
				return context.Jucators.ToList();
			}
		}
	}
}