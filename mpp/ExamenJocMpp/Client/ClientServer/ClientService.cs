using System;
using System.Collections.Generic;
using System.Data.Odbc;
using System.Windows;
using Client.ViewModels;
using Domain.Models;
using Interfaces;

namespace Client.ClientServer
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

		public void Astepta()
		{
			MainViewModel.StartAsteptare();
		}

		public void StartJoc(string oponent)
		{
			MainViewModel.OponentGasit(oponent);
		}

		public void AsteaptaRand()
		{
			MessageBox.Show("NU E RANDUL TAU!");
		}

		public void MutareNoua(string tablaNoua, int numarGeneratAdversar)
		{
			MainViewModel.NewMutare(tablaNoua, numarGeneratAdversar);
		}

		public void CautaOponent()
		{
			_server.HandleStartJoc(_player, this);
		}

		public void HandleMutare(int numar)
		{
			_server.HandleMutareNoua(_player, this, numar);
		}
	}
}