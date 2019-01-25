using System.Collections.Generic;
using System.Linq;
using Domain.Models;
using Domain.Repository;

namespace Server.Service
{
	public class JocService
	{
		private JocRepository _repository;

		public JocService()
		{
			_repository = new JocRepository();
		}

		public List<Joc> GetAll()
		{
			return _repository.GetAll().ToList();
		}

		public void Add(Joc c)
		{
			_repository.Add(c);
		}

		public void Update(Joc c)
		{
			_repository.Update(c);
		}

		public Joc Find(int id)
		{
			return _repository.Find(id);
		}
	}
}