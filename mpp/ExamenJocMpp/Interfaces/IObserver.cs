using System.Collections.Generic;

namespace Interfaces
{
	public interface IObserver
	{
		void Astepta();
		void StartJoc(string oponent);
		void AsteaptaRand();
		void MutareNoua(string tablaNoua, int numarGeneratAdversar);
	}
}