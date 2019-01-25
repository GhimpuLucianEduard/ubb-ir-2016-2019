namespace Server
{
	public class Joc
	{	
		public string JucatorO { get; set; }
		public string JucatorX { get; set; }
		public string JucatorActual { get; set; }
		public string[,] Semne { get; set; }

		public Joc(string o, string x)
		{
			JucatorO = o;
			JucatorX = x;
			JucatorActual = o;
			Semne = new string[3,3];
		}

		public string[,] Muta(int i, int j)
		{
			if (JucatorActual.Equals(JucatorO))
			{
				Semne[i, j] = "O";
				JucatorActual = JucatorX;
			}
			else
			{
				Semne[i, j] = "X";
				JucatorActual = JucatorO;
			}
			
			
			return Semne;
		}
	}
}