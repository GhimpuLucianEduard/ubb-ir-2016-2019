using System;
using System.Collections.Generic;

namespace Laborator1AI
{
	/// <summary>
	/// N-th tree used to store data
	/// </summary>
	/// <typeparam name="T"></typeparam>
	public class Tree<T> : IComparable<Tree<T>> where T : IComparable<T> 
	{
		/// <summary>
		/// Tree proprieties
		/// </summary>
		public T data;
		public LinkedList<Tree<T>> copii;

		/// <summary>
		/// Constructor
		/// </summary>
		/// <param name="datat">Data stored in the node</param>
		public Tree(T datat)
		{
			this.data = datat;
			this.copii = new LinkedList<Tree<T>>();
		}

		/// <summary>
		/// 
		/// </summary>
		/// <param name="entity">Child to be added</param>
		public void add(T entity)
		{
			copii.AddLast(new Tree<T>(entity));
		}

		/// <summary>
		/// Function used to traverse all the nodes od the tree
		/// </summary>
		/// <param name="node">First node, the head of the tree</param>
		/// <param name="visitor">Action to be executed on traverse</param>
		public void traverseAll(Tree<T> node, Action<Tree<T>> visitor)
		{
			visitor(node);
			foreach (Tree<T> kid in node.copii)
				traverseAll(kid, visitor);
		}

		/// <summary>
		/// Function used to traverse only the next level nodes
		/// </summary>
		/// <param name="node">First node, the head of the tree</param>
		/// <param name="visitor">Action to be executed on traverse</param>
		public void traverse(Tree<T> node, Action<Tree<T>> visitor)
		{
			foreach (Tree<T> kid in node.copii)
				visitor(kid);
		}

		/// <summary>
		/// Function to compare trees, compares the data in the trees
		/// </summary>
		/// <param name="other">Tree to compare with</param>
		/// <returns></returns>
		public int CompareTo(Tree<T> other)
		{
			return data.CompareTo(other.data);
		}
	}
}