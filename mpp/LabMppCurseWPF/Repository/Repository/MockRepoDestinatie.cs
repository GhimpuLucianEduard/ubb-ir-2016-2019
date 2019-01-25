using System.Collections.Generic;
using LabMppCurseWPF.Models;

namespace LabMppCurseWPF.Repository
{
	public class MockRepoDestinatie : IRepository<int,Destinatie>
	{
		private ValidatorDestinatie _validator;
		private List<Destinatie> _listDestinatii;

		public MockRepoDestinatie()
		{
			_validator = new ValidatorDestinatie();
			_listDestinatii = new List<Destinatie>
			{
				new Destinatie(1001,"Cluj"),
				new Destinatie(1002,"Bucuresti"),
				new Destinatie(1003,"Iasi"),
				new Destinatie(1004,"Galati")
			};
		}

		public Destinatie Find(int id)
		{
			return _listDestinatii.Find(d => d.Id == id);
		}

		public IEnumerable<Destinatie> FindAll()
		{
			return _listDestinatii;
		}

		public void Add(Destinatie entity)
		{
			throw new System.NotImplementedException();
		}

		public void Delete(int id)
		{
			throw new System.NotImplementedException();
		}

		public void Update(Destinatie entity)
		{
			throw new System.NotImplementedException();
		}
	}
}