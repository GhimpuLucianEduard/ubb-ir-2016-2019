using System;
using System.Collections.Generic;

namespace AEv2
{
	public class Cromozom : IComparable
	{
		public Gena Gena { get; set; }
		public Gena Gena2 { get; set; }

		public Cromozom()
		{
			Gena = new Gena();
			Gena2 = new Gena();
		}

		public void CreateRandom()
		{	
			Gena.CreateRandom();
			Gena2.CreateRandom();
			Reglare();
		}

		public override string ToString()
		{
			return Gena.ToString();
		}

		public int CompareTo(object obj)
		{
			var other = (Cromozom) obj;
			return other.Fitness().CompareTo(Fitness());
		}


		public void Reglare()
		{
			if (Config.BinaryToDecimal(Gena) >= Config.SizeDomeniu2)
			{	
				var deca = Config.BinaryToDecimal(Gena);
				var mod = deca%(Config.SizeDomeniu2);
				this.Gena = Config.DecimalToBinary(mod);
			}
			if (Config.BinaryToDecimal(Gena2) >= Config.SizeDomeniu2)
			{
				var deca = Config.BinaryToDecimal(Gena2);
				var mod = deca % (Config.SizeDomeniu2);
				this.Gena2 = Config.DecimalToBinary(mod);
			}
		}

		public double Fitness()
		{
			return Config.Domeniu2[Config.BinaryToDecimal(Gena),Config.BinaryToDecimal(Gena2)];
		}


		public Pair<Cromozom,Cromozom> Recombina(Cromozom parinte2)
		{
			var copil1 = new Cromozom();
			var copil2 = new Cromozom();

			for (var i = Config.MarimeGena-1; i >= 0; i--)
			{
				var ran = Config.RandomGenerator.Next();
				if (ran % 2 == 0)
				{
					copil1.Gena.Add(this.Gena[Config.RandomGenerator.Next(Config.MarimeGena-1)]);
					copil2.Gena.Add(parinte2.Gena[Config.RandomGenerator.Next(Config.MarimeGena-1)]);
					copil1.Gena2.Add(this.Gena[Config.RandomGenerator.Next(Config.MarimeGena - 1)]);
					copil2.Gena2.Add(parinte2.Gena[Config.RandomGenerator.Next(Config.MarimeGena - 1)]);
				}
				else
				{
					copil2.Gena.Add(this.Gena[Config.RandomGenerator.Next(Config.MarimeGena - 1)]);
					copil1.Gena.Add(parinte2.Gena[Config.RandomGenerator.Next(Config.MarimeGena - 1)]);
					copil2.Gena2.Add(this.Gena[Config.RandomGenerator.Next(Config.MarimeGena - 1)]);
					copil1.Gena2.Add(parinte2.Gena[Config.RandomGenerator.Next(Config.MarimeGena - 1)]);
				}
			}




			copil1.Reglare();
			copil2.Reglare();

			return new Pair<Cromozom, Cromozom>(copil1,copil2);
		}

		public void Mutatie()
		{
			for (int i = 0; i < Gena.Count-1; i++)
			{
				var ran = Config.RandomGenerator.NextDouble();
				if (ran < Config.PragMutatie)
				{
					if (Gena[i] == 0)
					{
						Gena[i] = 1;
					}
					else
					{
						Gena[i] = 0;
					}
				}
			}

			for (int i = 0; i < Gena2.Count - 1; i++)
			{
				var ran = Config.RandomGenerator.NextDouble();
				if (ran < Config.PragMutatie)
				{
					if (Gena2[i] == 0)
					{
						Gena2[i] = 1;
					}
					else
					{
						Gena2[i] = 0;
					}
				}
			}

			Reglare();
		}

	}

	
}