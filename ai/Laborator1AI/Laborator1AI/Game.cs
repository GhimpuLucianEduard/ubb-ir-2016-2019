using System;
using System.Collections.Generic;

namespace Laborator1AI
{
	public class Game
	{
		private Tree<Tabla> treeOfStates;

		public Game(Tabla tablaInitiala)
		{	

			this.treeOfStates = new Tree<Tabla>(tablaInitiala);
			generateTree(treeOfStates);
		}

		public void generateTree(Tree<Tabla> initialState)
		{
			if (getVecini(initialState.data).Count!=0)
			{
				getVecini(initialState.data).ForEach(v =>
				{
					initialState.add(v);

				});
				Action<Tree<Tabla>> act = tree =>
				{
					if (getVecini(tree.data).Count!=0)
					{
						generateTree(tree);
					}
				};
				initialState.traverse(initialState, act);
			}
			
			
		}

		public Tree<Tabla> getTree()
		{
			return treeOfStates;
		}

		

		public List<Tabla> getVecini(Tabla currentState)
		{
			List<Tabla> solutiiCanditat = new List<Tabla>();
			//currentState.PrintTabla();
			if (currentState.checkIfFull() == false)
			{
				for (int i = 1; i <= currentState.marime * currentState.marime; i++)
				{
					Tabla vecin = new Tabla(currentState.marime, currentState.CurrentCoordinates.i, currentState.CurrentCoordinates.j);
					vecin.populeazaTabla(currentState);
					vecin.addElement(i);
					if (vecin.isValidSudoku())
					{
						solutiiCanditat.Add(vecin);
					}
				}
			}
			
			return solutiiCanditat;
		}

		public List<Tabla> DFS()
		{	

			
			List<Tabla> solutiiFinale = new List<Tabla>();
			IDictionary <Tabla,bool> frecv = new Dictionary<Tabla,bool>();
			Stack<Tree<Tabla>> stack = new Stack<Tree<Tabla>>();
			stack.Push(getTree());
			while (stack.Count != 0)
			{
				Tree<Tabla> current = stack.Pop();
				frecv[current.data] = true;
				if (current.data.checkIfWin())
				{
					solutiiFinale.Add(current.data);
				}
				Action<Tree<Tabla>> act = tree =>
				{
//					if (!frecv.ContainsKey(tree.data))
//					{
//						stack.Push(tree);
//					}
					var exista = false;
					foreach (var item in frecv.Keys)
					{
						if (item.Equals(tree.data))
						{
							exista = true;
						}
					}

					if (exista == false)
					{
						stack.Push(tree);
					}

				};
				treeOfStates.traverse(current, act);

			}

			return solutiiFinale;
		}

		public Tabla GDFS()
		{

			IDictionary<Tabla, bool> frecv = new Dictionary<Tabla, bool>();
			PriorityQueue<Tree<Tabla>> priorityQueue = new PriorityQueue<Tree<Tabla>>();
			priorityQueue.Enqueue(treeOfStates);
			Tabla found = default(Tabla);

			while (priorityQueue.Count() != 0 && found==default(Tabla))
			{
				//Console.WriteLine(priorityQueue.Count());
				if (priorityQueue.Count() == 0)
				{
					return found;
				}

				Tree<Tabla> current = priorityQueue.Dequeue();
				frecv[current.data] = true;
				if (current.data.checkIfWin())
				{
					return found = current.data;
				}
				Action<Tree<Tabla>> act = tree =>
				{
					var exista = false;
					foreach (var item in frecv.Keys)
					{
						if (item.Equals(tree.data))
						{
							exista = true;
						}
					}

					if (exista == false)
					{
						priorityQueue.Enqueue(tree);
					}

				};
				treeOfStates.traverse(current, act);

			}

			return default(Tabla);
		}
	}
}