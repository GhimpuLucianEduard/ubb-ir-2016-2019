using System;
using System.Windows;
using Client.ClientServer;

namespace Client.ViewModels
{
	public class MainViewModel : BaseViewModel
	{
		private int _numarGenerat;

		public int NumarGenerat
		{
			get { return _numarGenerat; }
			set { SetValue(ref _numarGenerat, value);}
		}
		private int numarTura = 1;

		private string _tablaJoc;

		public string TablaJoc
		{
			get { return _tablaJoc; }
			set { SetValue(ref _tablaJoc, value);}
		}

		private Visibility _asteptareVisibility;

		public Visibility AsteptareVisibility
		{
			get { return _asteptareVisibility; }
			set { SetValue(ref _asteptareVisibility, value);}
		}
		private bool _startJocEnabled;

		public bool StartJocEnabled
		{
			get { return _startJocEnabled; }
			set { SetValue(ref _startJocEnabled, value);}
		}

		private ClientService _service;

		private string _numeJucator;

		public string NumeJucator
		{
			get { return _numeJucator; }
			set { SetValue(ref _numeJucator, value);}
		}

		private string _numeAdversar;

		public string NumeAdversar
		{
			get { return _numeAdversar; }
			set
			{
				SetValue(ref _numeAdversar,value);
			}
		}

		public MainViewModel(ClientService service)
		{
			_service = service ?? throw new ArgumentNullException(nameof(service));
			StartJocEnabled = true;
			AsteptareVisibility = Visibility.Hidden;
		}

		public void AfterLogin(string username)
		{
			NumeJucator = username;
		}

		public void OponentGasit(string oponent)
		{
			NumeAdversar = oponent;
			StartJocEnabled = false;
			AsteptareVisibility = Visibility.Hidden;
			TablaJoc = "*********";
		}

		public void StartAsteptare()
		{
			StartJocEnabled = false;
			AsteptareVisibility = Visibility.Visible;
		}

		public void CautaOponent()
		{
			_service.CautaOponent();
		}

		public void HandleMutare()
		{
			if (numarTura >= 6)
			{
				MessageBox.Show("Jocul s-a terminat!");
				return;
			}
			var rnd = new Random(DateTime.Now.Millisecond);
			var nr = rnd.Next(1, 3);
			_service.HandleMutare(nr);
		}

		public void NewMutare(string tablaNoua, int numarGeneratAdversar)
		{
			TablaJoc = tablaNoua;
			NumarGenerat = numarGeneratAdversar;
			numarTura++;
		}
	}
}