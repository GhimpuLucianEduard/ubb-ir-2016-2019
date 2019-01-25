namespace Intefaces
{
	public interface IObserver
	{
		void Astepta();
		void StartJoc(string semn, string oponent);
		void AsteaptaRand();
		void MutareNoua(string semn, int x, int y);
	}
}