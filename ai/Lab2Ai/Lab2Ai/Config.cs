using System;
using System.Collections.Generic;

namespace Lab2Ai
{
	public static class Config
	{	
		public static int CateSchimbari { get; set; }
		public static int MarimeGena { get; set; }
		public static int MarimeCromozom { get; set; }
		public static int MarimePopulatie { get; set; }
		public static int NumarIteratii { get; set; }
		public static double PragFitness { get; set; }
		public static Random RandomGenerator = new Random(29119318+DateTime.Now.Millisecond);
		public static List<double> Domeniu { get; set; }
		public static double PragMutatie { get; set; }

		public static int BinaryToDecimal(Gena binary)
		{
			var deca = 0;
			deca = Convert.ToInt32(binary.ToString(), 2);
			return deca;

		}

		public static Gena DecimalToBinary(int dec)
		{
			List<int> bools = new List<int>()
			{
				0,
				0,
				0,
				0,
				0,
				0,
				0,
				0
			};

			string s = Convert.ToString(dec, 2);
			var j = s.Length-1;
			for (int i = bools.Count - 1; i >= 0 && j>=0; i--)
			{

				bools[i] = s[j] - 48;
				j--;	
			}

			var g = new Gena();
			g.ValoareGena = bools;
			return g;

		}

	}
}