using System;
using Domain.Models;
using Server;

namespace Domain
{
	[Serializable]
	public class JucatorAux
	{
		public String Player { get; set; }
		public StarePlayer Stare { get; set; }
		public int Punctaj { get; set; } = 0;

		public JucatorAux(string player, StarePlayer stare, int punctaj)
		{
			Player = player;
			Stare = stare;
			Punctaj = punctaj;
		}
	}
}