using AEv2;

namespace PSO
{
	public class PSO
	{
		private int _marimePopulatie;
		private int _dimensiuni;
		private int _arieVecin;

		private double _pragFitness;
		private int _numarIteratii;

		public PSO(int marimePopulatie, int dimensiuni, int arieVecin, double pragFitness, int numarIteratii)
		{
			_marimePopulatie = marimePopulatie;
			_dimensiuni = dimensiuni;
			_arieVecin = arieVecin;
			_pragFitness = pragFitness;
			_numarIteratii = numarIteratii;
		}

		private bool Valid(Populatie p)
		{
			return p.GetBest().Fitness() > _pragFitness;
		}

		public void Run()
		{
			var pop = new Populatie(_marimePopulatie, _dimensiuni, _arieVecin);
			pop.Init();
			var nrIter = 0;
			System.Console.WriteLine("Cel mai bun: " + pop.GetBest().Fitness());
			System.Console.WriteLine("Iteratia nr : " + nrIter);
			System.Console.WriteLine("Cel mai bun: " + pop.GetBest().Fitness());
			while (Valid(pop) && nrIter < _numarIteratii)
			{
				
				pop.AddVecin();
				pop.Update();
				nrIter++;
				System.Console.WriteLine("Iteratia nr : " + nrIter);
				System.Console.WriteLine("Cel mai bun: " + pop.GetBest().Fitness());
			}
		}
	}
}