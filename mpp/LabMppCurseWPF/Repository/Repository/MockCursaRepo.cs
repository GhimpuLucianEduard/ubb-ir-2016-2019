using System;
using System.Collections.Generic;
using CurseMPP.Models;
using LabMppCurseWPF.Models;

namespace LabMppCurseWPF.Repository
{
	public class MockCursaRepo : IRepository<int,Cursa>
	{
		private ValidatorCursa _validator;
		private List<Cursa> _listaCurse;

		public MockCursaRepo()
		{
			_validator = new ValidatorCursa();
			_listaCurse = new List<Cursa>
			{
				new Cursa(11,1001,DateTime.Now,18,"Bucuresti"),
				new Cursa(12,1001,DateTime.Now,18,"Galati"),
				new Cursa(13,1002,DateTime.Now,18,"Timisoara"),
				new Cursa(14,1002,DateTime.Now,18,"Constanta"),
				new Cursa(15,1003,DateTime.Now,18,"Cluj"),
				new Cursa(16,1003,DateTime.Now,18,"Bucuresti"),
				new Cursa(17,1005,DateTime.Now,18,"Bucuresti")
			};
		}

		public Cursa Find(int id)
		{
			return _listaCurse.Find(c => c.Id == id);
		}

		public IEnumerable<Cursa> FindAll()
		{
			return _listaCurse;
		}

		public void Add(Cursa entity)
		{
			throw new System.NotImplementedException();
		}

		public void Delete(int id)
		{
			throw new System.NotImplementedException();
		}

		public void Update(Cursa entity)
		{	
			_listaCurse.ForEach(c =>
			{
				if (c.Id == entity.Id)
				{
					c = entity;
				}
			});
		}
	}
}