using System.Collections.Generic;
using System.Linq;
using Domain.Models;
using Domain.Repository;

namespace Server.Service
{
	public class MutareService
	{
		private MutareRepository _repository;

		public MutareService()
		{
			_repository = new MutareRepository();
		}

		public List<Mutare> GetAll()
		{
			return _repository.GetAll().ToList();
		}

		public void Add(Mutare c)
		{
			_repository.Add(c);
		}

		public void Update(Mutare c)
		{
			_repository.Add(c);
		}

		public Mutare Find(int id)
		{
			return _repository.Find(id);
		}
	}
}