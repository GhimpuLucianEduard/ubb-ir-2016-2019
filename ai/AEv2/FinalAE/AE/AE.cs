using System;
using System.Text;
using System.Xml.Schema;

namespace FinalAE.AE
{
	public class AE
	{
		private Populatie _populatieOutput1;
		private Populatie _populatieOutput2;
		private Populatie _populatieOutput3;

		public AE() {}

		public void Run()
		{
			_populatieOutput1 = new Populatie();
			_populatieOutput1.Initialize();
			var nrIteratii = 0;
			while (Valid(_populatieOutput1) && nrIteratii < ConfigurationClass.NumarIteratii)
			{
				Console.WriteLine(nrIteratii);
				Console.WriteLine(_populatieOutput1.GetBest().ToString());
				_populatieOutput1.Evolve();
				nrIteratii++;
			}
			Console.WriteLine("loss final ->");
			Console.WriteLine(_populatieOutput1.GetBest().Fitness());
			Console.WriteLine(_populatieOutput1.GetBest().ToString());
		}

		private bool Valid(Populatie populatie)
		{
			return populatie.GetBest().Fitness() > ConfigurationClass.PragFitness;
		}
	}
}