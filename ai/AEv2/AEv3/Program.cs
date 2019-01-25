using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using AEv2;

namespace AEv3
{
	class Program
	{
		static void Main(string[] args)
		{
			#region Populare

			var lines = System.IO.File.ReadAllLines("C:\\Users\\Deus\\Documents\\Visual Studio 2015\\Projects\\Lab2Ai\\Lab2Ai\\date\\02_date4.txt");

			foreach (var line in lines)
			{
				Console.WriteLine(line);
			}

			var size = Int32.Parse(lines[0]);
			var list = new List<double>();
			for (var j = 2; j <= 41; j++)
			{
				var numbers = lines[j].Split(Convert.ToChar(" "));
				foreach (var number in numbers)
				{
					if (number != "")
					{
						list.Add(Double.Parse(number));
					}

				}
			}

			Config.Domeniu2 = new double[40, 40];

			var k = 0;
			for (int i = 0; i < 40; i++)
			{
				for (int j = 0; j < 40; j++)
				{
					Config.Domeniu2[i, j] = list[k];
					k++;
				}
			}

			for (int i = 0; i < 40; i++)
			{
				for (int j = 0; j < 40; j++)
				{
					Console.Write(Config.Domeniu2[i, j] + " ");
				}
				Console.WriteLine(" ");
			}


			#endregion

			Config.MarimePopulatie = 50;
			Config.SizeDomeniu2 = 40;
			Config.MarimeGena = 8;
			Config.NumarIteratii = 2000;
			Config.PragFitness = -153;
			Config.CateSchimbari = 0;
			Config.PragMutatie = 0.10;
			var ae = new AlgoritmEvolutiv();
			ae.Run();
			Console.WriteLine("Rezultat dupa cate generatii: " + ae.NumarGeneratieCurenta);
			Console.WriteLine("Rezultat dupa cate schimbari deminim: " + Config.CateSchimbari);



		}
	}
}
