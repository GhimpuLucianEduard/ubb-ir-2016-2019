using System;
using System.Collections.Generic;

namespace REST.Models
{	
	[Serializable]
	public class Game
	{
		public string Castigator { get; set; }
		public int Puncte { get; set; }
		public List<string> Participanti { get; set; }

		public Game(string castigator, int puncte, List<string> participanti)
		{
			Castigator = castigator ?? throw new ArgumentNullException(nameof(castigator));
			Puncte = puncte;
			Participanti = participanti ?? throw new ArgumentNullException(nameof(participanti));
		}
	}
}