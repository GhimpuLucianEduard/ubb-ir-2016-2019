using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.InteropServices;
using System.Threading.Tasks;
using Domain;
using Domain.Models;
using Domain.Services;
using Interfaces;

namespace Server
{
	public class Server : MarshalByRefObject, IServer
	{
		private IDictionary<string, ServerAux> _clientsDictionary;
		private IDictionary<string, JucatorAux> _punctaje;
		private MainService _service;

		public Server()
		{
			_service = new MainService();
			_clientsDictionary = new Dictionary<string, ServerAux>();
			_punctaje = new Dictionary<string, JucatorAux>();
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

				_punctaje[player.Username] = new JucatorAux(logPlayer.Username, StarePlayer.Logat, 0);
				NotifyNewPlayer(_punctaje[player.Username]);
				_clientsDictionary[player.Username] = new ServerAux(logPlayer, client);
				client.SeePlayers(_punctaje.Values.ToList());
			}
			else
			{
				throw new Exception("alrd log");
			}

		}

		public void LogOut(string playerUsername, IObserver clientService)
		{
			_clientsDictionary.Remove(playerUsername);
			_punctaje.Remove(playerUsername);
			NotifyLogout(playerUsername);
		}

		private void NotifyLogout(string playerUsername)
		{
			_clientsDictionary.Values.ToList().ForEach(x =>
			{
				if (x != null)
				{
					x.Client.PlayerLogOut(playerUsername);
				}
				
			});
		}

		private void NotifyNewPlayer(JucatorAux jucatorAux)
		{
			_clientsDictionary.Values.ToList().ForEach(x =>
			{
				
				if (x != null)
				{
					x.Client.NewPlayer(jucatorAux);
				}
			});
		}
	}
		
	public class ServerAux
	{
		public Jucator Player { get; set; }
		public IObserver Client { get; set; }

		public ServerAux(Jucator player, IObserver client)
		{
			Player = player ?? throw new ArgumentNullException(nameof(player));
			Client = client ?? throw new ArgumentNullException(nameof(client));
		}
	}
}