using System.Collections.Generic;
using Domain;
using Domain.Models;

namespace Interfaces
{
	public interface IObserver
	{
		void SeePlayers(List<JucatorAux> jucatori);
		void NewPlayer(JucatorAux jucator);
		void PlayerLogOut(string player);
	}
}