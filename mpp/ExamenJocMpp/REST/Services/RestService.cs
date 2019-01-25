using System;
using System.Collections.Generic;
using System.Linq;
using Domain.Models;
using Domain.Repository;

namespace REST.Services
{
	public class RestService
	{
		private ExamenMppEntities _repository;

		public RestService()
		{
			_repository = new ExamenMppEntities();
		}

		public IstoricJoc GetIstoricJoc(int idJoc, int idJucator)
		{
			var joc = _repository.Jocs.Find(idJoc);
			if (joc == null)
			{
				return new IstoricJoc();
			}

			if (!joc.IdJucator1.Equals(idJucator) && !joc.IdJucator2.Equals(idJucator))
			{
				return new IstoricJoc();
			}
			var istoric = new IstoricJoc();
			istoric.NumarJoc = idJoc;
			if (joc.IdJucator1.Equals(idJucator))
			{
				istoric.NumeJucator = joc.Jucator.Username;
				istoric.UltimaPozitie = (int) joc.PozitieJucator1;
			}
			else
			{
				istoric.NumeJucator = joc.Jucator1.Username;
				istoric.UltimaPozitie = (int)joc.PozitieJucator2;
			}

			var numere = new List<int>();
			joc.Mutares.ToList().ForEach(x =>
			{
				if (x.IdJucator == idJucator)
				{
					if (x.NumarGenerat != null)
					{
						numere.Add((int)x.NumarGenerat);
					}
					
				}
			});

			istoric.NumereGenerate = numere;
			return istoric;
		}
	}

	public class IstoricJoc
	{
		public List<int> NumereGenerate { get; set; }
		public string NumeJucator { get; set; }
		public int NumarJoc { get; set; }
		public int UltimaPozitie { get; set; }

		public IstoricJoc()
		{

		}

		public IstoricJoc(List<int> numereGenerate, string numeJucator, int numarJoc, int ultimaPozitie)
		{
			NumereGenerate = numereGenerate ?? throw new ArgumentNullException(nameof(numereGenerate));
			NumeJucator = numeJucator ?? throw new ArgumentNullException(nameof(numeJucator));
			NumarJoc = numarJoc;
			UltimaPozitie = ultimaPozitie;
		}
	}
}