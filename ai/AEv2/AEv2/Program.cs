using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.InteropServices;
using System.Text;
using System.Threading.Tasks;

namespace AEv2
{
	class Program
	{
		static void Main(string[] args)
		{
			#region Config

			Config.MarimePopulatie = 6;
			Config.MarimeGena = 8;
			Config.NumarIteratii = 100;
			Config.PragFitness = -16.6817;
			Config.CateSchimbari = 0;
			Config.PragMutatie = 0.05;

			#endregion


			#region Populare Domeniu

			var lines = System.IO.File.ReadAllLines("C:\\Users\\Deus\\Documents\\Visual Studio 2015\\Projects\\Lab2Ai\\Lab2Ai\\date\\02_date3.txt");

			foreach (var line in lines)
			{
				Console.WriteLine(line);
			}

			var size = Int32.Parse(lines[0]);

			var numbers = lines[1].Split(Convert.ToChar(" "));


			Config.Domeniu = new List<double>(size);

			foreach (var number in numbers)
			{
				if (number != "")
				{
					Config.Domeniu.Add(Double.Parse(number));
				}
				
			}

			#endregion


			#region test

//			var genaTest = new Gena();
//			genaTest.CreateRandom();
//			Console.WriteLine("Gena de test in binar"+genaTest.ToString());
//			Console.WriteLine("Gena de test in decimal"+Config.BinaryToDecimal(genaTest));
//			Console.WriteLine("Gena de test din decimal in binary"+ Config.DecimalToBinary(Config.BinaryToDecimal(genaTest)));
//			
//			var cromozomTest = new Cromozom();
//			cromozomTest.CreateRandom();
//			Console.WriteLine("Cromozol tostring inainte de reglare binar: "+cromozomTest.ToString());
//			Console.WriteLine("Cromozol tostring inainte de reglare in deca: "+Config.BinaryToDecimal(cromozomTest.Gena));
//			cromozomTest.Reglare();
//			Console.WriteLine("Cromozol tostring dupa reglare binar: " + cromozomTest.ToString());
//			Console.WriteLine("Cromozol tostring dupa reglare in deca: " + Config.BinaryToDecimal(cromozomTest.Gena));
//
//			var populatieTest = new Populatie();
//			populatieTest.InitPopulatie();
//			Console.WriteLine(populatieTest.ToString());
//			populatieTest.ListaCromozomi.ForEach(x=>Console.WriteLine(x.Fitness()));
//
//			Console.WriteLine("Minim:"+populatieTest.GetBest().ToString());

//			var nor = 20;
//			while (nor > 0)
//			{
//				Console.WriteLine(populatieTest.SelecteazaParinte().ToString());
//				nor--;
//			}
			
//			Console.WriteLine("EVOLUTIE");
//			populatieTest.Evolve();
//			populatieTest.Evolve();
//			populatieTest.Evolve();
//			populatieTest.Evolve();
//			populatieTest.Evolve();
//			Console.WriteLine(populatieTest.ToString());
//			Console.WriteLine("Minim:" + populatieTest.GetBest().ToString());

//			var ae = new AlgoritmEvolutiv();
//			ae.Run();
//			Console.WriteLine("Rezultat dupa cate generatii: "+ae.NumarGeneratieCurenta);
//			Console.WriteLine("Rezultat dupa cate schimbari deminim: "+Config.CateSchimbari);

//	
//			var pop = new Populatie();
//			pop.InitPopulatie();
//			pop.ListaCromozomi.ForEach(x =>
//			{
//				Console.WriteLine(x);
//				Console.WriteLine(x.Fitness());
//			});
//			pop.Evolve();
//		
//			Console.WriteLine("Selectie");
//			//Console.WriteLine(pop.ToString());
//			pop.ListaCromozomi.ForEach(x =>
//			{
//				Console.WriteLine(x);
//				Console.WriteLine(x.Fitness());
//			});

			//			var ae = new AlgoritmEvolutiv();
//			ae.Run();
//			Console.WriteLine("Rezultat dupa cate generatii: "+ae.NumarGeneratieCurenta);
//			Console.WriteLine("Rezultat dupa cate schimbari deminim: "+Config.CateSchimbari);
	

			var ae = new AlgoritmEvolutiv();
			ae.Run();
			Console.WriteLine("Rezultat dupa cate generatii: "+ae.NumarGeneratieCurenta);
			Console.WriteLine("Rezultat dupa cate schimbari deminim: "+Config.CateSchimbari);

			#endregion

		}
	}
}
