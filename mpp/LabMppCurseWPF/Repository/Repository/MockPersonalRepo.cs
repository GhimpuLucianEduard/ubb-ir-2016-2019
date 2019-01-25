using System.Collections.Generic;
using LabMppCurseWPF.Models;
using LabMppCurseWPF.NET_Edition;

namespace LabMppCurseWPF.Repository
{
	public class MockPersonalRepo : IRepository<int,Personal>
	{
		private ValidatorPersonal _validatorPersonal;
		private List<Personal> _listaPersonal;
		public MockPersonalRepo()
		{
			_validatorPersonal = new ValidatorPersonal();

			_listaPersonal = new List<Personal>
			{
				new Personal(101,"admin","admin","popescu","ion"),
				new Personal(102,"admin2","admin2","popescu","ion"),
				new Personal(103,"admin3","admin3","popescu","ion"),
				new Personal(104,"admin4","admin4","popescu","ion"),
			};
		}

		public Personal Find(int id)
		{
			return _listaPersonal.Find(personal => personal.Id == id);
		}

		public IEnumerable<Personal> FindAll()
		{
			return _listaPersonal;
		}

		public void Add(Personal entity)
		{
			throw new System.NotImplementedException();
		}

		public void Delete(int id)
		{
			throw new System.NotImplementedException();
		}

		public void Update(Personal entity)
		{
			throw new System.NotImplementedException();
		}
	}
}