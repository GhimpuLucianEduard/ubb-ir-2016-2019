using System;
using System.Collections.Generic;

namespace FinalAE.AE
{
	public class Cromozom : IComparable
	{	
		public List<Gena> Gene { get; set; }

		public Cromozom()
		{
			Gene = new List<Gena>();
		}

		/// <param name="n">Numar de gene</param>
		public void Initialize(int numarGene)
		{
			for (var i = 0; i < numarGene; i++)
			{
				if (ConfigurationClass.RandomGenerator.Next() % 2 == 0)
				{
					Gene.Add(new Gena(ConfigurationClass.RandomGenerator.NextDouble()));
				}
				else
				{
					Gene.Add(new Gena(-1*ConfigurationClass.RandomGenerator.NextDouble()));
				}
			}
		}

		public double Fitness()
		{
			var loss = 0.0;
			
			for (var i = 0; i < 98; i++)
			{
				var sum = 0.0;
				for (var j = 0; j < 6; j++)
				{
					var sq = ConfigurationClass.Domeniu[i, j] * Gene[j].Valoare;
					sum += sq;
				}

				var lastValue = ConfigurationClass.Domeniu[i, 6 + ConfigurationClass.OutputVariable];
				loss = Math.Abs(sum - lastValue);
			}

			return loss;
		}

		public void Mutatie()
		{
			for (int i = 0; i < Gene.Count; i++)
			{
				var random = ConfigurationClass.RandomGenerator.NextDouble();
				if (random < ConfigurationClass.PragMutatie)
				{
					if (ConfigurationClass.RandomGenerator.Next() % 2 == 0)
					{
						Gene[i] = new Gena(-1 * Gene[i].Valoare);
					}
					else
					{
						Gene[i] = new Gena(ConfigurationClass.RandomGenerator.NextDouble());
					}
				}
			}
		}

		public List<Cromozom> Recombinare(Cromozom other)
		{
			var copii = new List<Cromozom>();

			var first = new Cromozom();
			var second = new Cromozom();

			for (var i = 0; i < Gene.Count; i++)
			{
				var genaNouaFirst = (this.Gene[i].Valoare + other.Gene[i].Valoare) / 2;
				var genaNoua2nd = (this.Gene[i].Valoare + other.Gene[i].Valoare) * 2;
				first.Gene.Add(new Gena(genaNouaFirst));
				second.Gene.Add(new Gena(genaNoua2nd));
			}
			copii.Add(first);
			copii.Add(second);
			return copii;
		}

		public int CompareTo(object obj)
		{
			var other = (Cromozom)obj;
			if (this.Fitness() - other.Fitness() < 0)
			{
				return -1;
			}
			else if (this.Fitness() - other.Fitness() == 0 )
			{
				return 0;
			}

			return 1;
		}

		public override string ToString()
		{
			var rez = "";
			Gene.ForEach(x => { rez += x.ToString() + " "; });
			return rez;
		}
	}
}