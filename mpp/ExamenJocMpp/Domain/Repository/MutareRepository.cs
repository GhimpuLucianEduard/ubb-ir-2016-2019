using System.Collections.Generic;
using System.Linq;
using Domain.Models;

namespace Domain.Repository
{
	public class MutareRepository
	{
		public Mutare Find(int id)
		{
			using (var context = new ExamenMppEntities())
			{
				return context.Mutares.Find(id);
			}
		}

		public IEnumerable<Mutare> GetAll()
		{
			using (var context = new ExamenMppEntities())
			{
				return context.Mutares.ToList();
			}
		}


		public void Add(Mutare j)
		{
			using (var context = new ExamenMppEntities())
			{
				context.Mutares.Add(j);
				context.SaveChanges();
			}
		}

		public void Update(Mutare m)
		{
			using (var context = new ExamenMppEntities())
			{
				var oldEntity = Find(m.Id);
				context.Entry(oldEntity).CurrentValues.SetValues(m);
				context.SaveChanges();
			}
		}
	}
}