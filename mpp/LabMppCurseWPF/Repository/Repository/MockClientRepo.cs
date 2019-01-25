using System.Collections.Generic;
using LabMppCurseWPF.Models;

namespace LabMppCurseWPF.Repository
{
	public class MockClientRepo : IRepository<int,Client>
	{
		private ValidatorClient _validator;
		private List<Client> _listaClienti;

		public MockClientRepo()
		{
			_validator = new ValidatorClient();
			_listaClienti = new List<Client>
			{
				new Client(23,"Ion","Ion"),
				new Client(24,"Miahai","Ionovici"),
				new Client(25,"Marcel","Ionescu"),
				new Client(26,"Andrei","Ionita")
			};
		}

		public Client Find(int id)
		{
			return _listaClienti.Find(x => x.Id == id);
		}

		public IEnumerable<Client> FindAll()
		{
			return _listaClienti;
		}

		public void Add(Client entity)
		{	
			_validator.Validate(entity);
			_listaClienti.Add(entity);
		}

		public void Delete(int id)
		{
			throw new System.NotImplementedException();
		}

		public void Update(Client entity)
		{
			throw new System.NotImplementedException();
		}
	}
}