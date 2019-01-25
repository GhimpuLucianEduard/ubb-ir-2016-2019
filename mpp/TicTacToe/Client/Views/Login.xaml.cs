using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Remoting.Channels;
using System.Runtime.Remoting.Channels.Tcp;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;
using Client.ViewModels;
using Intefaces;

namespace Client.Views
{
	/// <summary>
	/// Interaction logic for Login.xaml
	/// </summary>
	public partial class Login : Window
	{
		private ServiceClient _service;
		public Login()
		{	
			Start();
			InitializeComponent();
		}

		public void Start()
		{
			BinaryServerFormatterSinkProvider serverProv = new BinaryServerFormatterSinkProvider();
			serverProv.TypeFilterLevel = System.Runtime.Serialization.Formatters.TypeFilterLevel.Full;
			BinaryClientFormatterSinkProvider clientProv = new BinaryClientFormatterSinkProvider();
			IDictionary props = new Hashtable();

			props["port"] = 0;
			TcpChannel channel = new TcpChannel(props, clientProv, serverProv);
			ChannelServices.RegisterChannel(channel, false);
			IServer server =
				(IServer)Activator.GetObject(typeof(IServer), "tcp://192.168.19.1:3291/Curse");
			_service = new ServiceClient(server);
		}

		private void Button_Click(object sender, RoutedEventArgs e)
		{
			var pass = PasswordBox.Password;
			var user = EntryUsername.Text;
			if (string.IsNullOrWhiteSpace(user) || string.IsNullOrWhiteSpace(pass))
			{
				MessageBox.Show("Nume sau parola invalide!", "WARNING", MessageBoxButton.OK, MessageBoxImage.Error);
				return;
			}

			try
			{
				var isLoginOk = _service.HandleLogin(user, pass);
				if (isLoginOk)
				{
					MessageBox.Show("Login efectuat cu succes!", "WARNING", MessageBoxButton.OK, MessageBoxImage.Error);
					var vm = new MainViewModel(_service);
					_service.MainViewModel = vm;
					var newWindow = new Tabla(vm);
					newWindow.Show();
					Application.Current.MainWindow.Close();
					Application.Current.MainWindow = newWindow;
				}
				else
				{
					MessageBox.Show("Autentificare nu s-a putut efectua!", "WARNING", MessageBoxButton.OK, MessageBoxImage.Error);
				}
			}
			catch (Exception ex)
			{
				MessageBox.Show(ex.Message, "WARNING", MessageBoxButton.OK, MessageBoxImage.Error);
			}
		}
	}
}
