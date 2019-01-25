using System;
using System.Collections.Generic;

namespace FinalAE
{
	public static class ConfigurationClass
	{
		public static double[,] Domeniu { get; set; }
		public static Random RandomGenerator = new Random(29119318 + DateTime.Now.Millisecond);
		public static double PragMutatie { get; set; }
		public static int OutputVariable { get; set; }
		public static int MarimePopulatie { get; set; }
		public static int NumarGene { get; set; }
		public static int NumarIteratii { get; set; }
		public static double PragFitness { get; set; }
	}
}