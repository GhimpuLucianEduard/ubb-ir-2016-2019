using System;
using System.Collections.Generic;
using System.Runtime.CompilerServices;
using FinalAE.AE;

namespace FinalAE
{
	class Program
	{
		static void Main(string[] args)
		{
			#region Populare

			ConfigurationClass.PragFitness = -200;
			var lines = System.IO.File.ReadAllLines("C:\\Users\\Deus\\Documents\\Visual Studio 2015\\Projects\\AEv2\\FinalAE\\text.txt");

			foreach (var line in lines)
			{
				Console.WriteLine(line);
			}

			var list = new List<double>();
			for (var j = 0; j <= 97; j++)
			{
				var numbers = lines[j].Split(Convert.ToChar(","));
				foreach (var number in numbers)
				{
					if (number != "")
					{
						list.Add(Double.Parse(number));
					}

				}
			}

			ConfigurationClass.Domeniu = new double[98, 10];

			var k = 0;
			for (var i = 0; i < 97; i++)
			{
				for (var j = 0; j < 10; j++)
				{
					ConfigurationClass.Domeniu[i, j] = list[k];
					k++;
				}
			}

			for (var i = 0; i < 97; i++)
			{
				for (var j = 0; j < 10; j++)
				{
					Console.Write(ConfigurationClass.Domeniu[i, j] + " ");
				}
				Console.WriteLine(" ");
			}

		
			#endregion


			ConfigurationClass.MarimePopulatie = 30;
			ConfigurationClass.NumarGene = 7;
			ConfigurationClass.PragMutatie = 0.5;
			ConfigurationClass.NumarIteratii = 50;

			ConfigurationClass.OutputVariable = 1;


			var AE = new AE.AE();
			AE.Run();
		}
	}
}
