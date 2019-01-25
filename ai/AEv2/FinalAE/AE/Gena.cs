namespace FinalAE.AE
{
	public class Gena
	{
		public Gena(double valoare)
		{
			Valoare = valoare;
		}

		public double Valoare { get; set; }

		public override string ToString()
		{
			return Valoare.ToString();
		}
	}
}