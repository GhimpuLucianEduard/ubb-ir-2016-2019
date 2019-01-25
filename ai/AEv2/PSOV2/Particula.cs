using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography;
using AEv2;

namespace PSO
{
	public class Particula
	{
		private List<int> _pozitie;
		private int _viteza;
		private List<Particula> _listaPozitii;
		private List<Particula> _vecini;

		public Particula(int n)
		{
			_viteza = 0;
			_listaPozitii = new List<Particula>();
			_vecini = new List<Particula>();
			_pozitie = new List<int>(n);
		}

		public void Create(int n)
		{
			for (var i = 0; i < n; i++)
			{
				_pozitie.Add(Config.RandomGenerator.Next(Config.Domeniu.Count));
			}
		}

		public double Fitness()
		{
			return Config.Domeniu2[_pozitie[0], _pozitie[1]]; 
		}

		public int GetPozitieVecinCelMaiBun()
		{
			return _vecini[0].GetPozitie();
		}

		public int GetPozitie()
		{
			return _pozitie[0];
		}

		public int GetBestPozitie()
		{
			return _listaPozitii[0].GetPozitie();
		}

		public void ModificareViteza()
		{
			_viteza = Config.FactorDeInertie * _viteza +
			          Config.FactorDeInvatatareCognitiv * Config.RandomGenerator.Next(2) * (GetBestPozitie() - GetPozitie()) +
			          Config.FactorDeInvatareSocial * Config.RandomGenerator.Next(2) *
			          (GetPozitieVecinCelMaiBun() - GetPozitie());
			Corectare();
		}

		private void Corectare()
		{
			if (_pozitie[0] + _viteza >= Config.Domeniu.Count || _pozitie[0] + _viteza < 0)
			{
				_pozitie[0] = _pozitie[0] % Config.Domeniu.Count;
			}
			else
			{
				ModificaPozitie();
			}
		}

		public void ModificaPozitie()
		{
			_pozitie[0] += _viteza;
		}

		public void Intoarcere()
		{
			_viteza = Config.FactorDeInertie * _viteza +
			          Config.FactorDeInvatatareCognitiv * -Config.RandomGenerator.Next(2) * (GetBestPozitie() - GetPozitie()) +
			          Config.FactorDeInvatareSocial * -Config.RandomGenerator.Next(2) *
			          (GetPozitieVecinCelMaiBun() - GetPozitie());
			Corectare();
		}

		public void AddVecin(Particula vecin)
		{
			_vecini.Add(vecin);
			_vecini = _vecini.OrderBy(x => x.Fitness()).ToList();
		}

		public void AddPozitie(Particula p)
		{
			_listaPozitii.Add(p);
			_listaPozitii = _listaPozitii.OrderBy(x => x.Fitness()).ToList();
		}

		public void ResetVecin()
		{
			_vecini.Clear();
		}

	}
}