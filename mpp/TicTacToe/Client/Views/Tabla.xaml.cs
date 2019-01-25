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
using System.Windows.Navigation;
using System.Windows.Shapes;
using Client.ViewModels;

namespace Client.Views
{
	/// <summary>
	/// Interaction logic for Tabla.xaml
	/// </summary>
	public partial class Tabla : Window
	{
		public MainViewModel vm { get; set; }

		public Tabla(MainViewModel viewModel)
		{
			InitializeComponent();
			DataContext = viewModel;
			vm = viewModel;
			vm.TablaJoc = this.tabla;
		}

		private void Btn_OnClick(object sender, RoutedEventArgs e)
		{
			vm.Muta(sender);
		}
	}
}
