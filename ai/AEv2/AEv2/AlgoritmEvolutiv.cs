using System;

namespace AEv2
{
	public class AlgoritmEvolutiv
	{
		public int NumarGeneratieCurenta { get; set; }
		private Populatie _generatieCurenta;


		public void Run()
		{
			NumarGeneratieCurenta = 0;
			var populatieInitiala = new Populatie();
			populatieInitiala.InitPopulatie();
			_generatieCurenta = populatieInitiala;

			Console.WriteLine("Populatia initiala:");
			populatieInitiala.ListaCromozomi.ForEach(x =>
			{
				Console.WriteLine(x.Fitness());
				Console.WriteLine(x.ToString());
			});
			Console.WriteLine("=================");



			while (Valid())
			{
				var oldBest = _generatieCurenta.GetBest();
				_generatieCurenta.Evolve();
				var newBest = _generatieCurenta.GetBest();
				if (Config.BinaryToDecimal(oldBest.Gena) != Config.BinaryToDecimal(newBest.Gena))
				{
					Config.CateSchimbari++;
				}
				NumarGeneratieCurenta++;
				Console.WriteLine(NumarGeneratieCurenta.ToString());
				Console.WriteLine("Cel mai bun:" + _generatieCurenta.GetBest());
				Console.WriteLine("Fitness cel mai bun:" + _generatieCurenta.GetBest().Fitness());
			}
		}

		private bool Valid()
		{
			var condi1 = _generatieCurenta.GetBest().Fitness() > Config.PragFitness;
			var condi2 = NumarGeneratieCurenta < Config.NumarIteratii;
			return condi1 && condi2;

		}
	}
}