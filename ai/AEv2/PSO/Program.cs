using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using AEv2;

namespace PSO
{
	class Program
	{
		static void Main(string[] args)
		{
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

			//			var p1 = new Particula(1);
			//			p1.Create(1);
			//			var p2 = new Particula(1);
			//			p2.Create(1);
			//			var p3 = new Particula(1);
			//			p3.Create(1);
			//			var p4 = new Particula(1);
			//			p4.Create(1);
			//			var p5 = new Particula(1);
			//			p5.Create(1);
			//
			//
			//			var listaTest = new List<Particula>();
			//			listaTest.Add(p1);
			//			listaTest.Add(p2);
			//			listaTest.Add(p3);
			//			listaTest.Add(p4);
			//			listaTest.Add(p5);
			//			listaTest = listaTest.OrderByDescending(particula =>particula.Fitness() ).ToList();


			var pso = new PSO(5, 2, 10, -100, 15);
			pso.Run();
		}
	}
}
