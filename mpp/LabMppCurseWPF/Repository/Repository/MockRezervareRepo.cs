using System.Collections.Generic;
using CurseMPP.Models;
using LabMppCurseWPF.Models;

namespace LabMppCurseWPF.Repository
{
	public class MockRezervareRepo : IRepository<int, Rezervare>
	{
		private ValidatorRezervare _validator;
		private List<Rezervare> _listaRezervari;


		public MockRezervareRepo()
		{
			_validator = new ValidatorRezervare();
			_listaRezervari = new List<Rezervare>
			{
				new Rezervare(111,23,11,1),
				new Rezervare(112,24,11,2),
				new Rezervare(113,24,12,1),
				new Rezervare(114,24,13,3),
				new Rezervare(115,23,13,1)
			};
		}

		public Rezervare Find(int id)
		{
			throw new System.NotImplementedException();
		}

		public IEnumerable<Rezervare> FindAll()
		{
			return _listaRezervari;
		}

		public void Add(Rezervare entity)
		{
			_listaRezervari.Add(entity);
		}

		public void Delete(int id)
		{
			throw new System.NotImplementedException();
		}

		public void Update(Rezervare entity)
		{
			throw new System.NotImplementedException();
		}
	}
}