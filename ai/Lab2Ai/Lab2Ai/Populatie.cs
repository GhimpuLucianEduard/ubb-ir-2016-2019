using System;
using System.Collections.Generic;
using System.Linq;

namespace Lab2Ai
{
	public class Populatie
	{
		public List<Cromozom> ListaPopulatie { get; set; }

		public void InitPopulatie()
		{	
			ListaPopulatie = new List<Cromozom>(Config.MarimePopulatie);
			for (var i = 0; i < Config.MarimePopulatie; i++)
			{
				var cromozom = new Cromozom();
				cromozom.CreateRandom();
				ListaPopulatie.Add(cromozom);
			}
		}

		public Cromozom GestBest()
		{	
			var min = ListaPopulatie[0];
			foreach (var individ in ListaPopulatie)
			{	
				Console.WriteLine("Fitnes maxim actuak:"+min.Fitness());
				Console.WriteLine("Fitnes individ de comaprat:"+individ.Fitness());
				if (individ.Fitness() < min.Fitness())
				{
					min = individ;
				}
				Console.WriteLine("Fitnesc maxim nou:" + min.Fitness());
			}

			return min;
		}

		public void Evolve()
		{
			Recombinare();
			Mutatie();
			Selectie();
		}

		private void Selectie()
		{
			ListaPopulatie.OrderBy(x => x.Fitness()).ToList();
			var newPop = new List<Cromozom>();
			var count = Config.MarimePopulatie-1;
			var index = 0;
			while (count>=0)
			{	
				
				newPop.Add(ListaPopulatie[index]);
				count--;
				index++;
			}

//			while (count >= Config.MarimePopulatie/2)
//			{
//				newPop.Add(ListaPopulatie[count]);
//				count--;
//			}
//			
//			while (count >=0)
//			{
//				newPop.Add(ListaPopulatie[Config.MarimePopulatie-count-1]);
//				count--;
//			}
//			for (int i = 0; i < Config.MarimePopulatie/2; i++)
//			{
//				newPop.Add(SelecteazaParinte());
//			}

			ListaPopulatie = newPop;
		}

		private void Mutatie()
		{
			var ran = 0.1;
			ListaPopulatie.ForEach(x =>
			{
				ran = Config.RandomGenerator.NextDouble();
				if (ran <= Config.PragMutatie)
				{
					Console.WriteLine("==========================");
					Console.WriteLine("==========================");
					Console.WriteLine("A MUTAT UN CROMOZOM");
					Console.WriteLine("==========================");
					Console.WriteLine("==========================");
					x.MutatieCromozom();
				}
			});
		}

		private void Recombinare()
		{
			for (var i = 0; i < Config.MarimePopulatie / 2; i++)
			{
				var parinte1 = SelecteazaParinte();
				var parinte2 = SelecteazaParinte();
				var copil = Cromozom.Recombina(parinte1, parinte2);
				if(Config.BinaryToDecimal(copil.ListaGene[0])>=Config.Domeniu.Count)
				{
					//copii.First.ListaGene[0] = Config.DecimalToBinary(Config.Domeniu.Count - 1);
					var modulo = Config.BinaryToDecimal(copil.ListaGene[0]) % (Config.Domeniu.Count - 1);
					var y = Config.DecimalToBinary(modulo);
					copil.ListaGene[0] = y;
				}
//				if (Config.BinaryToDecimal(copii.Second.ListaGene[0]) >= Config.Domeniu.Count)
//				{
//					//copii.Second.ListaGene[0] = Config.DecimalToBinary(Config.Domeniu.Count - 1);
//					var modulo = Config.BinaryToDecimal(copii.Second.ListaGene[0]) % (Config.Domeniu.Count - 1);
//					var y = Config.DecimalToBinary(modulo);
//					copii.Second.ListaGene[0] = y;
//				}
				//ListaPopulatie.Add(copii.First);
				ListaPopulatie.Add(copil);
			}
		}

		private Cromozom SelecteazaParinte()
		{
			var index =  Config.RandomGenerator.Next() % (ListaPopulatie.Count - 1);
			while (index >= Config.Domeniu.Count)
			{	
				
				index = Config.RandomGenerator.Next();
			}
			Console.WriteLine("Am ales pe:" + index);
			return ListaPopulatie[index];
		}
	}
}