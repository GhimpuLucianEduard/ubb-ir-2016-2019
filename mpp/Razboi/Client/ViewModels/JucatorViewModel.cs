using Domain;
using Domain.Models;
using Server;

namespace Client.ViewModels
{
	public class JucatorViewModel : BaseViewModel
	{
		private string _username;
		private StarePlayer _stare;
		private int _punctaj;

		public string Username
		{
			get { return _username; }
			set { SetValue(ref _username,value);}
		}

		public int Punctaj
		{
			get { return _punctaj; }
			set { SetValue(ref _punctaj, value); }
		}

		public StarePlayer Stare
		{
			get { return _stare; }
			set { SetValue(ref _stare, value); }
		}


		public JucatorViewModel(JucatorAux juc)
		{
			Username = juc.Player;
			Stare = juc.Stare;
			Punctaj = juc.Punctaj;
		}

		public override string ToString()
		{
			return base.ToString();
		}
	}
}