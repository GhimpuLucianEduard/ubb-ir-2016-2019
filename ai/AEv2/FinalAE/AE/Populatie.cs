using System.Collections.Generic;
using System.Linq;

namespace FinalAE.AE
{
	public class Populatie
	{
		private List<Cromozom> _populatie;

		public Populatie()
		{
			_populatie = new List<Cromozom>();
		}

		public void Initialize()
		{
			for (var i = 0; i < ConfigurationClass.MarimePopulatie; i++)
			{
				var cromozom = new Cromozom();
				cromozom.Initialize(ConfigurationClass.NumarGene);
				_populatie.Add(cromozom);
			}
			_populatie.Sort();
		}

		public Cromozom GetBest()
		{
			_populatie.Sort();
			return _populatie[0];
		}

		private void Mutatie()
		{
			_populatie.ForEach(c =>
			{
				var random = ConfigurationClass.RandomGenerator.NextDouble();
				if (random <= ConfigurationClass.PragMutatie)
				{
					c.Mutatie();
				}
			});
		}

		private void Recombinare()
		{
			for (int i = 0; i < ConfigurationClass.MarimePopulatie; i++)
			{
				var tata = _populatie[ConfigurationClass.RandomGenerator.Next(ConfigurationClass.MarimePopulatie - 1)];
				var mama = _populatie[ConfigurationClass.RandomGenerator.Next(ConfigurationClass.MarimePopulatie - 1)];
				tata.Recombinare(mama).ForEach(c =>
				{
					_populatie.Add(c);
				});
			}
		}

		private void Selectie()
		{
			_populatie.Sort();
			_populatie = _populatie.Take(ConfigurationClass.MarimePopulatie).ToList();
		}

		public void Evolve()
		{
			Recombinare();
			Mutatie();
			Selectie();
		}

		public override string ToString()
		{
			var pop = "";
			_populatie.ForEach(x => { pop += x.ToString() + "  "; });
			return pop;
		}
	}
}