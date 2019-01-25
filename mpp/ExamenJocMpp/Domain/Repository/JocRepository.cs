using System;
using System.Collections.Generic;
using System.Data.Entity.Core.Objects;
using System.Linq;
using Domain.Models;

namespace Domain.Repository
{
	public class JocRepository
	{
		public Joc Find(int id)
		{
			using (var context = new ExamenMppEntities())
			{
				return context.Jocs.Find(id);
			}
		}

		public IEnumerable<Joc> GetAll()
		{
			using (var context = new ExamenMppEntities())
			{
				return context.Jocs.ToList();
			}
		}

		public void Add(Joc j)
		{
			using (var context = new ExamenMppEntities())
			{
				context.Jocs.Add(j);
				context.SaveChanges();
			}
		}

		public void Update(Joc m)
		{
			using (var context = new ExamenMppEntities())
			{
				var oldEntity = Find(m.Id);
				oldEntity.PozitieJucator1 = m.PozitieJucator1;
				oldEntity.PozitieJucator2 = m.PozitieJucator2;
				context.SaveChanges();
			}
		}
	}
}