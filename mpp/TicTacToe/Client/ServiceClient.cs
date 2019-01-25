using System;
using System.Windows;
using Client.ViewModels;
using Domain.Models;
using Intefaces;

namespace Client
{
	public class ServiceClient : MarshalByRefObject, IObserver
	{
		private IServer _server;
		private Player _player;
		public MainViewModel MainViewModel { get; set; }

		public ServiceClient(IServer server)
		{
			_server = server ?? throw new ArgumentNullException(nameof(server));
		}

		public bool HandleLogin(string userName, string passwd)
		{
			try
			{
				var player = new Player
				{
					Username = userName,
					Pass = passwd
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
		
		public void HandleMutare(int x, int y, string semnd)
		{
			_server.HandleMutare(_player, this, x, y, semnd);
		}

		public void HandleStartJoc()
		{
			_server.HandleStartJoc(_player, this);
		}

		public void Astepta()
		{
			StartAsteptare();
		}

		private void StartAsteptare()
		{
			MainViewModel.StartAsteptare();
		}

		public void StartJoc(string semn, string oponent)
		{
			MainViewModel.OponentGasit(semn, oponent);
		}

		public void AsteaptaRand()
		{
			MessageBox.Show("Nu e randul tau!!");
		}

		public void MutareNoua(string semn, int x, int y)
		{
			MainViewModel.MutareNoua(x, y, semn);
		}
	}
}