using System;
using System.Collections.Generic;

namespace Lab2Ai
{
	public class Gena
	{	
		public List<int> ValoareGena { get; set; }

		public Gena()
		{
			ValoareGena = new List<int>(Config.MarimeGena);
		}

		public void CreateRandom()
		{
			ValoareGena = new List<int>(Config.MarimeGena);
			for (var i = 0; i < Config.MarimeGena; i++)
			{
				ValoareGena.Add(Config.RandomGenerator.Next() % 2);
			}
		}

		public override string ToString()
		{
			var genaToShow = "";
			ValoareGena.ForEach(x => { genaToShow += x.ToString(); });
			return genaToShow;
		}

		public void MutatieGena()
		{
			var ran = 0.1;
			ValoareGena.ForEach(x =>
			{
				ran = Config.RandomGenerator.NextDouble();
				if (ran <= Config.PragMutatie)
				{
					if (x == 0)
					{
						x = 1;
					}
					else
					{
						x = 0;
					}

				}
			});
		}
	}
}