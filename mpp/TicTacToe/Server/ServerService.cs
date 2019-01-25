using System;
using System.Collections.Generic;
using System.Linq;
using Domain.Models;
using Intefaces;
using Server.Services;

namespace Server
{
	public class ServerService :  MarshalByRefObject, IServer
	{
		private IDictionary<string, Joc> _jocuriActive;
		private IDictionary<string, IObserver> _clientsDictionary;
		public IDictionary<string, StareJucator> JucatoriOnline { get; set; }
		private MainService _service;

		public ServerService()
		{
			_service = new MainService();
			_clientsDictionary = new Dictionary<string, IObserver>();
			JucatoriOnline = new Dictionary<string, StareJucator>();
			_jocuriActive = new Dictionary<string, Joc>();
		}

		public void Login(Player player, IObserver client)
		{
			var logPlayer = _service.HandleLogin(player);
			Console.WriteLine($"Player: {player.Username} incearca sa se conecteze...");
			if (logPlayer!=default(Player))
			{
				if (_clientsDictionary.ContainsKey(player.Username))
				{
					throw new Exception("User already logged in!");
				}

				_clientsDictionary[player.Username] = client;
				JucatoriOnline[player.Username] = StareJucator.Logat;

			}
			else
			{
				throw new Exception("Sign in failed!");
			}
		}

		public void HandleStartJoc(Player player, IObserver client)
		{
			JucatoriOnline[player.Username] = StareJucator.InAsteptare;
			var oponent = string.Empty;
			foreach (var keyValuePair in JucatoriOnline)
			{
				if (keyValuePair.Value.Equals(StareJucator.InAsteptare) && !keyValuePair.Key.Equals(player.Username))
				{
					oponent = keyValuePair.Key;
				}
			}


			if (oponent.Equals(string.Empty))
			{
				_clientsDictionary[player.Username].Astepta();
			}
			else
			{
				_clientsDictionary[player.Username].StartJoc("X", oponent);
				_clientsDictionary[oponent].StartJoc("O",player.Username);
				JucatoriOnline[player.Username] = StareJucator.InJoc;
				JucatoriOnline[oponent] = StareJucator.InJoc;
				_jocuriActive[player.Username+oponent] = new Joc(oponent, player.Username);
			}
		}

		public void HandleMutare(Player player, IObserver client, int x, int y, string semn)
		{
			var joc = _jocuriActive.FirstOrDefault((pair => pair.Key.Contains(player.Username)));
			if (!joc.Value.JucatorActual.Equals(player.Username))
			{
				client.AsteaptaRand();
			}
			else
			{
				joc.Value.Muta(x, y);
				var jct1 = _clientsDictionary[joc.Value.JucatorO];
				var jct2 = _clientsDictionary[joc.Value.JucatorX];
				jct1.MutareNoua(semn, x, y);
				jct2.MutareNoua(semn, x, y);
			}
		}
	}
}