using System;
using System.Collections.Generic;

namespace AEv2
{
	public class Populatie
	{
		public List<Cromozom> ListaCromozomi { get; set; }

		public void InitPopulatie()
		{
			ListaCromozomi = new List<Cromozom>(Config.MarimePopulatie);
			for (var i = 0; i < Config.MarimePopulatie; i++)
			{
				var cromozom = new Cromozom();
				cromozom.CreateRandom();
				ListaCromozomi.Add(cromozom);
			}
		}


		public override string ToString()
		{
			var toReutrn = "";
			ListaCromozomi.ForEach(x => { toReutrn += x.ToString() +"\n"; });
			return toReutrn;
		}

		public Cromozom GetBest()
		{
			var minim = ListaCromozomi[0];
			ListaCromozomi.ForEach(x =>
			{
				if (minim.CompareTo(x) < 0)
				{
					minim = x;
				}
			});

			return minim;
		}


		public void Evolve()
		{
			Recombinare();
			Mutatie();
			Selectie();
		}

		public void Selectie()
		{
			ListaCromozomi.Sort();
			ListaCromozomi.Reverse();

			var newPop = new List<Cromozom>();
			var size = 0;
			while (size<Config.MarimePopulatie)
			{
				newPop.Add(ListaCromozomi[size]);
				size++;
			}

			ListaCromozomi = newPop;

		}

		private void Mutatie()
		{
			ListaCromozomi.ForEach(x =>
			{
				var ran = Config.RandomGenerator.NextDouble();
				if (ran < Config.PragMutatie)
				{
					x.Mutatie();
				}
			});
		}

		private void Recombinare()
		{
			for (var i = 0; i < Config.MarimePopulatie / 2; i++)
			{
				var parinte1 = SelecteazaParinte();
				var parinte2 = SelecteazaParinte();
				var copii = parinte1.Recombina(parinte2);
				ListaCromozomi.Add(copii.First);
				ListaCromozomi.Add(copii.Second);
			}
			
		}

		public Cromozom SelecteazaParinte()
		{
			var index = Config.RandomGenerator.Next(0, ListaCromozomi.Count);
			//Console.WriteLine("Am ales pe:" + index);
			return ListaCromozomi[index];
		}


	}
}