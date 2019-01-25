using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Laborator1AI
{
	class Program
	{
		static void Main(string[] args)
		{

			
			// the code that you want to measure comes here

			Random rnd = new Random();

//						Tabla tablaJoc = new Tabla(3,0,0);
//						Console.WriteLine("Has tabla 1:"+tablaJoc.GetHashCode());
//						Tabla tablaJoc2 = new Tabla(3,0,0);
//						Tabla tablaJoc3 = new Tabla(3,0,0);
//						Tabla tablaJoc4 = new Tabla(3,0,0);
//						Console.WriteLine("Has tabla 2:" + tablaJoc2.GetHashCode());
//						Console.WriteLine(tablaJoc.Equals(tablaJoc2));
//						Console.WriteLine("=======================prima========================");
//						tablaJoc.populeazaRandom();
//						tablaJoc2.populeazaRandom();
//						tablaJoc3.populeazaRandom();
//						tablaJoc4.populeazaRandom();
//						tablaJoc3.addElement(-2);
//						tablaJoc3.addElement(-2);
//						tablaJoc3.addElement(-2);
//						tablaJoc3.addElement(-1);
//						tablaJoc4.addElement(-1);
//						Console.WriteLine(tablaJoc.Equals(tablaJoc2));
//						Console.WriteLine("=======================adoua========================");
//						
//						Tree<Tabla> tree1 = new Tree<Tabla>(tablaJoc);
//						Tree<Tabla> tree2 = new Tree<Tabla>(tablaJoc2);
//						Tree<Tabla> tree3 = new Tree<Tabla>(tablaJoc3);
//						Tree<Tabla> tree4 = new Tree<Tabla>(tablaJoc4);
//
//
//						PriorityQueue<Tree<Tabla>> pq = new PriorityQueue<Tree<Tabla>>();
//			pq.Enqueue(tree1);
//			pq.Enqueue(tree2);
//			pq.Enqueue(tree3);
//			pq.Enqueue(tree4);
//			
//						while (pq.Count() > 0)
//						{
//							Console.WriteLine("===============================================");
//							pq.Dequeue().data.PrintTabla();
//							Console.WriteLine("===============================================");
//						}
//			
//			
//						tablaJoc.PrintTabla();
//						Console.WriteLine("===============================================");
//						tablaJoc.populeazaRandom();
//			
//						tablaJoc.PrintTabla();
//						Console.WriteLine(tablaJoc.isValidSudoku());
//						Console.WriteLine(tablaJoc.CurrentCoordinates.i);
//						Console.WriteLine(tablaJoc.CurrentCoordinates.j);
//						Console.WriteLine(tablaJoc.checkIfFull());
//						Console.WriteLine("===============================================");



			//
			//			Game game = new Game(tablaJoc);
			//			List<Tabla> list = game.getVecini(game.getTree().data);
			//			list.ForEach(x=>x.PrintTabla());
			//			Game game = new Game(tablaJoc);

			Tabla tablaJoc = new Tabla(3, 0, 0);
			tablaJoc.populeazaRandom();
			tablaJoc.PrintTabla();
			Console.WriteLine("===============================================");
			Game game = new Game(tablaJoc);

			var watch = System.Diagnostics.Stopwatch.StartNew();
			game.DFS().ForEach(x =>
			{
				x.PrintTabla();
				Console.WriteLine("===============================================");
			});

			watch.Stop();
			var elapsedMsDFS = watch.ElapsedMilliseconds;
			
			Console.WriteLine("Timp BDF: " + elapsedMsDFS);
			Console.WriteLine("===============================================");
			var watch2 = System.Diagnostics.Stopwatch.StartNew();

			game.GDFS().PrintTabla();

			watch2.Stop();
			var elapsedMsGBFS = watch2.ElapsedMilliseconds;
			Console.WriteLine("===============================================");
			Console.WriteLine("Timp Greedy: " + elapsedMsGBFS);
			Console.WriteLine("===============================================");

		}
	}
}
