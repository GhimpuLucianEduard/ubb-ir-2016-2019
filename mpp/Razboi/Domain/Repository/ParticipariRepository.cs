using System.Collections.Generic;
using System.Linq;
using Domain.Models;

namespace Domain.Repository
{
	public class ParticipariRepository
	{
		public Participare Find(int id)
		{
			using (var context = new RazboiEntities())
			{
				return context.Participares.Find(id);
			}
		}

		public IEnumerable<Participare> GetAll()
		{
			using (var context = new RazboiEntities())
			{
				return context.Participares.ToList();
			}
		}
	}
}