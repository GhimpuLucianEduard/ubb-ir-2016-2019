using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO.Ports;

namespace Lab2Ai
{
	public class Cromozom
	{
		public List<Gena> ListaGene { get; set; }

		public Cromozom()
		{
			ListaGene = new List<Gena>(Config.MarimeCromozom);
		}

		public void CreateRandom()
		{
	
			for (var i = 0; i < Config.MarimeCromozom; i++)
			{
				var gena = new Gena();
				gena.CreateRandom();
				while (Config.BinaryToDecimal(gena) >= Config.Domeniu.Count)
				{
					gena.CreateRandom();
				}
				ListaGene.Add(gena);
			}
			
		}

		public override string ToString()
		{
			var cromoToShow = "";
			ListaGene.ForEach(x => { cromoToShow += x.ToString(); });
			return cromoToShow;
		}

		public double Fitness()
		{
			return Config.Domeniu[Config.BinaryToDecimal(ListaGene[0])];
		}

		public static  Cromozom Recombina(Cromozom parinte1, Cromozom parinte2)
		{

				var aux = new Cromozom();
				var aux2 = new Cromozom();
				aux.ListaGene.Add(new Gena());
				aux2.ListaGene.Add(new Gena());

			var medie = ((Config.BinaryToDecimal(parinte1.ListaGene[0]) + Config.BinaryToDecimal(parinte2.ListaGene[0]))/2);
			Console.WriteLine("Mama+tata=" + Config.BinaryToDecimal(parinte1.ListaGene[0]) +
			                  Config.BinaryToDecimal(parinte2.ListaGene[0]));
			
			var genaCopil = Config.DecimalToBinary(medie);
			aux.ListaGene[0] = genaCopil;
			Console.WriteLine("Mama:"+Config.BinaryToDecimal(parinte1.ListaGene[0]));
			Console.WriteLine("Tata:" + Config.BinaryToDecimal(parinte1.ListaGene[0]));
			Console.WriteLine("A iesit copilul cu index:"+medie);
//				for (int i = 0; i <= 7; i++)
//				{
//					if (i <= 4)
//					{
//						aux.ListaGene[0].ValoareGena.Add(parinte1.ListaGene[0].ValoareGena[i]);
//					}
//					else
//					{
//						aux2.ListaGene[0].ValoareGena.Add(parinte2.ListaGene[0].ValoareGena[i]);
//					}
//
//				}
//
//				for (int i = 0; i <= 7; i++)
//				{
//					if (i > 4)
//					{
//						aux.ListaGene[0].ValoareGena.Add(parinte2.ListaGene[0].ValoareGena[i]);
//					}
//					else
//					{
//						aux2.ListaGene[0].ValoareGena.Add(parinte1.ListaGene[0].ValoareGena[i]);
//					}
//
//				}
			


			//return new Pair<Cromozom, Cromozom>(aux,aux2);
			return aux;

		}

		public void MutatieCromozom()
		{
			var ran = 0.1;
			ListaGene.ForEach(x =>
			{
				//ran = Config.RandomGenerator.NextDouble();
//				if (ran <= Config.PragMutatie)
//				{

					//					x.MutatieGena();
					//					if (Config.BinaryToDecimal(x)>=Config.Domeniu.Count)
					//					{
					////						x = Config.DecimalToBinary(Config.Domeniu.Count - 1);
					//						var modulo = Config.BinaryToDecimal(x) % (Config.Domeniu.Count - 1);
					//						var y = Config.DecimalToBinary(modulo);
					//						x = y;
					//
					//					}
					Console.WriteLine("==========================");
					Console.WriteLine("==========================");
					Console.WriteLine("A MUTAT o gENA");
					Console.WriteLine("Index vechi:"+Config.BinaryToDecimal(x));
					
					Console.WriteLine("==========================");
					Console.WriteLine("==========================");

				var interval = Config.RandomGenerator.Next(0, Config.Domeniu.Count - 1);
				Console.WriteLine("Generat random index mutat: "+interval);
				//					var interval = (Config.Domeniu.Count) / 5;
				//					Console.WriteLine("Interval:"+ (Config.Domeniu.Count - 1) / 5);
				//					var indexMutat = 0;
				//
				//					indexMutat = indexMutat = Config.BinaryToDecimal(x) + interval;
				//
				//					if (indexMutat >= Config.Domeniu.Count-1)
				//					{
				//						indexMutat = Config.BinaryToDecimal(x) - 2*interval;
				//					}


				x = Config.DecimalToBinary(interval);
					Console.WriteLine("Index nou:" + Config.BinaryToDecimal(x));
//				}
			});
		}
	}
}