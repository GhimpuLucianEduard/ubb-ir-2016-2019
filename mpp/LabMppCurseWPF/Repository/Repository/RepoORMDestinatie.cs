using System.Collections.Generic;
using System.Data.Entity;
using System.Data.Entity.Core.Objects;
using System.Linq;
using CurseMPP.Models;
using LabMppCurseWPF.Models;

namespace LabMppCurseWPF.Repository
{
	public class RepoORMDestinatie : IRepository<int,Destinatie>
	{
		private DbContextCurse _context;

		public Destinatie Find(int id)
		{
			return _context.Destinatii.Find(id);
		}

		public IEnumerable<Destinatie> FindAll()
		{
			return _context.Destinatii.ToList();
		}

		public void Add(Destinatie entity)
		{
			_context.Destinatii.Add((entity));
			_context.SaveChanges();
		}

		public void Delete(int id)
		{
			_context.Destinatii.Remove(Find(id));
			_context.SaveChanges();
		}

		public void Update(Destinatie entity)
		{
			var oldEntity = Find(entity.Id);
			_context.Entry(oldEntity).CurrentValues.SetValues(entity);
			_context.SaveChanges();
		}

		public RepoORMDestinatie()
		{
			_context = new DbContextCurse();
		}
	}
}