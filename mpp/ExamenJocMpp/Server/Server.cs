using System;
using System.Collections.Generic;
using System.Linq;
using Domain.Models;
using Interfaces;
using Server.GameEngine;
using Server.Service;

namespace Server
{
	public class Server : MarshalByRefObject, IServer
	{
		private IDictionary<string, ServerAux> _clientsDictionary;
		private IDictionary<string, GameSession> _jocuriActive;
		private MainService _service;

		public Server()
		{
			_service = new MainService();
			_clientsDictionary = new Dictionary<string, ServerAux>();
			_jocuriActive = new Dictionary<string, GameSession>();
		}


		public void Login(Jucator player, IObserver client)
		{
			var logPlayer = _service.JucatorSerice.HandleLogin(player);
			Console.WriteLine($"Player: {player.Username} incearca sa se conecteze...");
			if (logPlayer != default(Jucator))
			{
				if (_clientsDictionary.ContainsKey(player.Username))
				{
					throw new Exception("alrd log");
				}

				_clientsDictionary[player.Username] = new ServerAux(logPlayer, client);
			}
			else
			{
				throw new Exception("cant log in");
			}

		}

		public void HandleStartJoc(Jucator player, IObserver client)
		{
			_clientsDictionary[player.Username].Stare = StareJucator.InAsteptare;
			var oponent = string.Empty;
			foreach (var keyValuePair in _clientsDictionary)
			{	
				if (keyValuePair.Value.Stare.Equals(StareJucator.InAsteptare) && !keyValuePair.Key.Equals(player.Username))
				{
					oponent = keyValuePair.Key;
				}
			}


			if (oponent.Equals(string.Empty))
			{
				_clientsDictionary[player.Username].Client.Astepta();
			}
			else
			{
				_clientsDictionary[player.Username].Client.StartJoc(oponent);
				_clientsDictionary[oponent].Client.StartJoc(player.Username);
				_clientsDictionary[player.Username].Stare = StareJucator.InJoc;
				_clientsDictionary[oponent].Stare = StareJucator.InJoc;

				var newJoc = new Joc();
				newJoc.IdJucator1 = _clientsDictionary[player.Username].Player.Id;
				newJoc.IdJucator2 = _clientsDictionary[oponent].Player.Id;
				newJoc.PozitieJucator1 = 0;
				newJoc.PozitieJucator2 = 0;
				_service.JocService.Add(newJoc);

				_jocuriActive[player.Username + oponent] = new GameSession(player.Username, oponent, player.Username, 1, newJoc);

			}
		}

		public void HandleMutareNoua(Jucator player, IObserver client, int numarGenerat)
		{
			var joc = _jocuriActive.FirstOrDefault((pair => pair.Key.Contains(player.Username)));
			if (joc.Value.Runda >= 6)
			{
				return;
			}
			if (!joc.Value.JucatorCurent.Equals(player.Username))
			{
				client.AsteaptaRand();
			}
			else
			{
				var tabla = joc.Value.Muta(numarGenerat);
				var jct1 = _clientsDictionary[joc.Value.Jucator1];
				var jct2 = _clientsDictionary[joc.Value.Jucator2];
				jct1.Client.MutareNoua(tabla, numarGenerat);
				jct2.Client.MutareNoua(tabla, numarGenerat);

				var newJoc = new Joc();
				newJoc.Id = joc.Value.JocDb.Id;
				newJoc.IdJucator1 = joc.Value.JocDb.IdJucator1;
				newJoc.IdJucator2 = joc.Value.JocDb.IdJucator2;
				newJoc.PozitieJucator1 = joc.Value.PozitieJucator1;
				newJoc.PozitieJucator2 = joc.Value.PozitieJucator2;
				_service.JocService.Update(newJoc);

				var mut = new Mutare();
				mut.NumarGenerat = numarGenerat;
				mut.IdJoc = newJoc.Id;

				if (joc.Value.JucatorCurent.Equals(player.Username))
				{
					mut.IdJucator = joc.Value.JocDb.IdJucator2;
				}
				else
				{
					mut.IdJucator = joc.Value.JocDb.IdJucator1;
				}

				_service.MutareService.Add(new Mutare());

			}

		}

		public void LogOut(string playerUsername, IObserver clientService)
		{
			throw new NotImplementedException();
		}
	}

	public class ServerAux
	{	
		public Jucator Player { get; set; }
		public IObserver Client { get; set; }
		public StareJucator Stare { get; set; }

		public ServerAux(Jucator player, IObserver client)
		{
			Player = player ?? throw new ArgumentNullException(nameof(player));
			Client = client ?? throw new ArgumentNullException(nameof(client));
			Stare = StareJucator.Logat;
		}
	}
}