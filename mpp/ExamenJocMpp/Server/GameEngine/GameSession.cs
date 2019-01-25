using System;
using System.Text;
using Domain.Models;
using Server.Service;

namespace Server.GameEngine
{
	public class GameSession
	{	
		public Joc JocDb { get; set; }
		public string Jucator1 { get; set; }
		public string Jucator2 { get; set; }
		public string TablaJoc { get; set; } = "*********";
		public int PozitieJucator1 { get; set; } = 0;
		public int PozitieJucator2 { get; set; } = 0;
		public string JucatorCurent { get; set; }
		public int Runda { get; set; } = 1;

		public GameSession(string jucator1, string jucator2, string jucatorCurent, int runda, Joc jc)
		{
			Jucator1 = jucator1 ?? throw new ArgumentNullException(nameof(jucator1));
			Jucator2 = jucator2 ?? throw new ArgumentNullException(nameof(jucator2));
			JucatorCurent = jucatorCurent ?? throw new ArgumentNullException(nameof(jucatorCurent));
			Runda = runda;
			JocDb = jc;
		}

		public string Muta(int numarGenerat)
		{
			var indexCurent = JucatorCurent.Equals(Jucator1) ? PozitieJucator1 : PozitieJucator2;
			char semn = JucatorCurent.Equals(Jucator1) ? 'A' : 'B';
			char charMutare = TablaJoc[indexCurent + numarGenerat];

			if (charMutare.Equals('*'))
			{
				var sb = new StringBuilder(TablaJoc);
				sb[indexCurent + numarGenerat] = semn;
				TablaJoc = sb.ToString();

				var sb2 = new StringBuilder(TablaJoc);
				sb2[indexCurent] = '*';
				TablaJoc = sb2.ToString();

			}
			else
			{
				var last = indexCurent + numarGenerat - 1;
				if (last < 0)
				{
					var sb = new StringBuilder(TablaJoc);
					sb[0] = semn;
					TablaJoc = sb.ToString();

					var sb2 = new StringBuilder(TablaJoc);
					sb2[indexCurent] = '*';
					TablaJoc = sb.ToString();
				}
				else
				{
					var sb = new StringBuilder(TablaJoc);
					sb[indexCurent+numarGenerat-1] = semn;
					TablaJoc = sb.ToString();

					var sb2 = new StringBuilder(TablaJoc);
					sb2[indexCurent] = '*';
					TablaJoc = sb2.ToString();
				}
			}
			
			for (var i = 0; i < TablaJoc.Length; i++)
			{
				if (TablaJoc[i].Equals('A'))
				{
					PozitieJucator1 = i;
				}

				if (TablaJoc[i].Equals('B'))
				{
					PozitieJucator2 = i;
				}

			}

			if (JucatorCurent.Equals(Jucator1))
			{
				JucatorCurent = Jucator2;
			}
			else
			{
				JucatorCurent = Jucator1;
			}

			return TablaJoc;
		}
	}
}