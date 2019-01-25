using System;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Input;
using System.Windows.Navigation;

namespace Client.ViewModels
{
	public class MainViewModel : BaseViewModel
	{
		private string[,] semne;
		private string semn00 = "---";
		private string semn01 = "---";
		private string semn02 = "---";
		private string semn10 = "---";
		private string semn11 = "---";
		private string semn12 = "---";
		private string semn20 = "---";
		private string semn21 = "---";
		private string semn22 = "---";

		public string Semn00
		{
			get => semn00;
			set => SetValue(ref semn00, value);
		}

		public string Semn01
		{
			get => semn01;
			set => SetValue(ref semn01, value);
		}

		public string Semn02
		{
			get => semn02;
			set => SetValue(ref semn02, value);
		}

		public string Semn10
		{
			get => semn10;
			set => SetValue(ref semn10, value);
		}

		public string Semn11
		{
			get => semn11;
			set => SetValue(ref semn11, value);
		}

		public string Semn12
		{
			get => semn12;
			set => SetValue(ref semn12, value);
		}

		public string Semn20
		{
			get => semn20;
			set => SetValue(ref semn20, value);
		}
		public string Semn21
		{
			get => semn21;
			set => SetValue(ref semn21, value);
		}
		public string Semn22
		{
			get => semn22;
			set => SetValue(ref semn22, value);
		}

		private ServiceClient _service;
		public ICommand StartJocCommand { get; private set; }

		public Grid TablaJoc { get; set; }

		private Visibility _isAsteptare;
		public Visibility IsAsteptare
		{
			get => _isAsteptare;
			set => SetValue(ref _isAsteptare, value);
		}

		private Visibility _isButtonEnabled;
		private Visibility IsButtonEnabled
		{
			get => _isButtonEnabled;
			set => SetValue(ref _isButtonEnabled, value);
		}

		private Visibility _tablaVisibility;
		private Visibility TablaVisibility
		{
			get => _tablaVisibility;
			set => SetValue(ref _tablaVisibility, value);
		}

		public MainViewModel(ServiceClient service)
		{
			TablaVisibility = Visibility.Hidden;
			IsButtonEnabled = Visibility.Visible;
			_service = service;
			IsAsteptare = Visibility.Hidden;
			StartJocCommand = new BasicCommand(StartJoc);
			semne = new string[3,3];
			semne[0, 0] = Semn00;
			semne[0, 1] = Semn01;
			semne[0, 2] = Semn02;
			semne[1, 0] = Semn10;
			semne[1, 1] = Semn11;
			semne[1, 2] = Semn12;
			semne[2, 0] = Semn20;
			semne[2, 1] = Semn21;
			semne[2, 2] = Semn22;
			
		}

		private void StartJoc()
		{
			_service.HandleStartJoc();
			IsButtonEnabled = Visibility.Hidden;
		}

		public void StartAsteptare()
		{
			IsAsteptare = Visibility.Visible;
		}

		private string _oponent;

		public string Oponent
		{
			get => _oponent;
			set => SetValue(ref _oponent, value);
		}

		private string _semn;

		public string Semn
		{
			get => _semn;
			set => SetValue(ref _semn, value);
		}

		public void OponentGasit(string semn, string oponent)
		{
			IsAsteptare = Visibility.Hidden;
			Oponent = oponent;
			Semn = semn;
			TablaVisibility = Visibility.Visible;
		}

		public void Muta(object sender)
		{
			var button = sender as Button;
			var x = Grid.GetRow(button);
			var y = Grid.GetColumn(button);
			_service.HandleMutare(x, y, Semn);
		}

		public void MutareNoua(int x, int y, string semn)
		{
			semne[x, y] = semn;
			var prop = "Semn" + x + y;
			this.GetType().GetProperty(prop).SetValue(this, semn);
		}
	}
}