using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;

namespace PSO
{
	public class Populatie
	{
		private List<Particula> _populatie;
		private int _marimePopulatie;
		private int _arieVecin;
		private int _dimensiuni;

		public Populatie(int nrpop, int nrdim, int arie)
		{
			_populatie = new List<Particula>();
			_marimePopulatie = nrpop;
			_arieVecin = arie;
			_dimensiuni = nrdim;
		}

		public void Init()
		{
			for (int i = 0; i < _marimePopulatie; i++)
			{
				var p = new Particula(_dimensiuni);
				p.Create(_dimensiuni);
				_populatie.Add(p);
			}
		}

		public Particula GetBest()
		{
			_populatie = _populatie.OrderBy(x => x.Fitness()).ToList();
			return _populatie[0];
		}

		public void AddVecin()
		{
			_populatie.ForEach(x =>
			{
				x.ResetVecin();
				_populatie.ForEach(y =>
				{
					if (Math.Abs(y.GetPozitie() - x.GetPozitie()) <= _arieVecin)
					{
						x.AddVecin(y);
					}
				});
			});
		}

		public void Update()
		{
			_populatie.ForEach(x =>
			{
				x.AddPozitie(x);
				x.ModificareViteza();
			});
		}

	}
}