using System;
using System.Collections.Generic;
using Client.ViewModels;
using Domain;
using Domain.Models;
using Interfaces;

namespace Client
{
	public class ClientService : MarshalByRefObject, IObserver
	{
		private IServer _server;
		private Jucator _player;
		public MainViewModel MainViewModel { get; set; }

		public ClientService(IServer server)
		{
			_server = server ?? throw new ArgumentNullException(nameof(server));
		}

		public bool HandleLogin(string userName, string passwd)
		{
			try
			{
				var player = new Jucator()
				{
					Username = userName,
					Parola = passwd
				};
				_server.Login(player, this);
				_player = player;
				return true;
			}
			catch (Exception e)
			{
				return false;
			}

		}

		public void SeePlayers(List<JucatorAux> jucatori)
		{
			MainViewModel.SetList(jucatori);
		}

		public void NewPlayer(JucatorAux jucator)
		{
			MainViewModel.NewPlayer(jucator);
		}

		public void PlayerLogOut(string player)
		{
			if (MainViewModel != null)
			{
				MainViewModel.PlayerLeft(player);
			}	
		}

		public void LogOut()
		{
			_server.LogOut(_player.Username, this);
		}
	}
}