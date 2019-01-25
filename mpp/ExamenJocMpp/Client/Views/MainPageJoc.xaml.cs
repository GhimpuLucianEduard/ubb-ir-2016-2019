using System;
using System.Collections.Generic;
using System.Linq;
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

namespace Client.Views
{
	/// <summary>
	/// Interaction logic for MainPageJoc.xaml
	/// </summary>
	public partial class MainPageJoc : Window
	{	
		public MainViewModel mainViewModel { get; set; }
		public MainPageJoc(MainViewModel vm, string username)
		{
			InitializeComponent();
			DataContext = vm;
			vm.AfterLogin(username);
			mainViewModel = vm;
		}

		private void Button_Click(object sender, RoutedEventArgs e)
		{
			mainViewModel.CautaOponent();
		}

		private void Button_Click_1(object sender, RoutedEventArgs e)
		{
			mainViewModel.HandleMutare();
		}
	}
}
