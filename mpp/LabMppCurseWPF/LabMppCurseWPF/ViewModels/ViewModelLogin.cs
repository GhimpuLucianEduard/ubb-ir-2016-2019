using System.ComponentModel;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Input;
using LabMppCurseWPF.Commands;
using LabMppCurseWPF.View;

namespace LabMppCurseWPF.ViewModels
{	
	/// <summary>
	/// ViewModel pentru Login
	/// </summary>
	public class ViewModelLogin : BasicViewModel
	{
		private ServiceClient _serviceClient;
		private PasswordBox passwordBox;
		#region Propietati
		public ViewModelLogin(ServiceClient service)
		{
			_serviceClient = service;
			HandleLoginCommand = new BasicCommandWithParameter(Login);
		}

		public string Username { get; set; }
		public string Password { get; set; }
		#endregion

		#region Commands

		/// <summary>
		/// Comanda care executa loginul
		/// </summary>
		public ICommand HandleLoginCommand { get; set; }

		#endregion

		/// <summary>
		/// Handle Login
		/// </summary>
		/// <param name="obj"></param>
		private void Login(object obj)
		{
			var pwBox = obj as PasswordBox;
			if (string.IsNullOrWhiteSpace(Username) || string.IsNullOrWhiteSpace(pwBox.Password))
			{
				MessageBox.Show("Nume sau parola invalide!", "WARNING", MessageBoxButton.OK, MessageBoxImage.Error);
			}
			else
			{
				if (_serviceClient.HandleLogin(Username, pwBox.Password))
				{
					MessageBox.Show("Login efectuat cu succes!", "Succes", MessageBoxButton.OK, MessageBoxImage.Information);

					var win = new MainView(_serviceClient);
					win.Show();
					Application.Current.MainWindow.Close();
					Application.Current.MainWindow = win;

				}
				else
				{
					MessageBox.Show("Nume sau parola invalide!", "WARNING", MessageBoxButton.OK, MessageBoxImage.Error);
				}

			}
		}



	}
}