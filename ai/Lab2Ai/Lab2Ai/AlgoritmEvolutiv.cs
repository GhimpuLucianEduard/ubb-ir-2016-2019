using System;
using System.Xml.Schema;

namespace Lab2Ai
{
	public class AlgoritmEvolutiv
	{
		public int NumarGeneratieCurenta { get; set; }
		private Populatie _generatieCurenta;
		public void run()
		{
			NumarGeneratieCurenta = 0;
			var populatieInitiala = new Populatie();
			populatieInitiala.InitPopulatie();
			_generatieCurenta = populatieInitiala;

			Console.WriteLine("Populatia initiala:");
			populatieInitiala.ListaPopulatie.ForEach(x =>
			{	
				Console.WriteLine(x.Fitness());
				Console.WriteLine(x.ToString());
			});
			Console.WriteLine("=================");

			

			while (Valid())
			{
				var oldBest = _generatieCurenta.GestBest();
				_generatieCurenta.Evolve();
				var newBest = _generatieCurenta.GestBest();
				if (Config.BinaryToDecimal(oldBest.ListaGene[0]) != Config.BinaryToDecimal(newBest.ListaGene[0]))
				{
					Config.CateSchimbari++;
				}
				NumarGeneratieCurenta++;
				Console.WriteLine(NumarGeneratieCurenta.ToString());
				Console.WriteLine("Cel mai bun:"+_generatieCurenta.GestBest());
				Console.WriteLine("Fitness cel mai bun:"+_generatieCurenta.GestBest().Fitness());
			}
			



		}

		private bool Valid()
		{
			var condi1 = _generatieCurenta.GestBest().Fitness() > Config.PragFitness;
			var condi2 = NumarGeneratieCurenta < Config.NumarIteratii;
			return condi1 && condi2;

		}
	}
}