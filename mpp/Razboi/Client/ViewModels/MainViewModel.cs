using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Windows;
using Client.Views;
using Domain;
using Domain.Models;

namespace Client.ViewModels
{
	public class MainViewModel : BaseViewModel
	{
		public ObservableCollection<JucatorViewModel> Jucatori { get; set; }
		private ClientService _service;

		public MainViewModel(ClientService service)
		{
			_service = service ?? throw new ArgumentNullException(nameof(service));
			Jucatori = new ObservableCollection<JucatorViewModel>();
		}

		public void SetList(List<JucatorAux> jucatori)
		{
			Jucatori.Clear();
			jucatori.ForEach(x =>
			{
				var item = new JucatorViewModel(x)
				{
					Username = x.Player,
					Stare = x.Stare,
					Punctaj = x.Punctaj
				};
				Jucatori.Add(item);
			});
		}

		public void Reload()
		{
			var ceva = "dsad";
		}

		public void NewPlayer(JucatorAux jucator)
		{
			App.Current.Dispatcher.Invoke((Action)delegate // <--- HERE
			{
				Jucatori.Add(new JucatorViewModel(jucator));
			});
		}

		public void LogOut()
		{	
			var newWindow = new LoginPage();
			newWindow.Show();
			Application.Current.MainWindow.Close();
			Application.Current.MainWindow = newWindow;
			_service.LogOut();
		}

		public void PlayerLeft(string player)
		{	
			Jucatori.ToList().ForEach(x =>
			{
				if (x.Username.Equals(player))
				{
					App.Current.Dispatcher.Invoke((Action)delegate // <--- HERE
					{
						Jucatori.Remove(x);
					});
				}
			});
		}
	}
}