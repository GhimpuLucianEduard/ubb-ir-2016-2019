namespace Lab2Ai
{
	public class Pair<M,N>
	{
		public M First { get; set; }	
		public N Second { get; set; }

		public Pair(M first, N second)
		{
			First = first;
			Second = second;
		}
	}
}