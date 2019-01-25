using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab2Ai
{
	class Program
	{
		static void Main(string[] args)
		{
			var lines = System.IO.File.ReadAllLines("C:\\Users\\Deus\\Documents\\Visual Studio 2015\\Projects\\Lab2Ai\\Lab2Ai\\date\\02_date3.txt");

			// Display the file contents by using a foreach loop.
			System.Console.WriteLine("Contents of WriteLines2.txt = ");
			foreach (string line in lines)
			{
				// Use a tab to indent each line of the file.
				Console.WriteLine( line);
			}

			var size = Int32.Parse(lines[0]);

			var numbers = lines[1].Split(Convert.ToChar(" "));

			
			Config.Domeniu = new List<double>(size);

			foreach (var number in numbers)
			{
				Config.Domeniu.Add(Double.Parse(number));
			}

			Config.CateSchimbari = 0;
			Config.MarimeCromozom = 1;
			Config.MarimeGena = 8;
			Config.MarimePopulatie = 4;
			Config.NumarIteratii = 1000;
			Config.PragFitness = -8;
			Config.PragMutatie = 0.55;
			
			var ae = new AlgoritmEvolutiv();
			ae.run();

			Console.WriteLine("==========================");
			Console.WriteLine("==========================");
			Console.WriteLine("Au avut loc atatea schimbari de maxim: "+Config.CateSchimbari);
			Console.WriteLine("==========================");
			Console.WriteLine("==========================");
		}
	}
}
