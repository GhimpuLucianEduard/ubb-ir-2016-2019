using System;
using System.Collections.Generic;
using System.Linq;
using RestWepApiMppCurse.Models;

namespace RestWepApiMppCurse.Repo
{
	public class DbRepoCursa
	{
		private DbContextCurse _context;

		public Cursa Find(int id)
		{
			return _context.Curse.Find(id);
		}

		public IEnumerable<Cursa> FindAll()
		{
			return _context.Curse.ToList();
		}

		public void Add(Cursa entity)
		{
			_context.Curse.Add((entity));
			_context.SaveChanges();
		}

		public void Delete(int id)
		{
			_context.Curse.Remove(Find(id));
			_context.SaveChanges();
		}

		public void Update(Cursa entity)
		{
			var oldEntity = Find((int) entity.Id);
			_context.Entry(oldEntity).CurrentValues.SetValues(entity);
			_context.SaveChanges();
		}

		public DbRepoCursa()
		{
			_context = new DbContextCurse();
		}
	}
}