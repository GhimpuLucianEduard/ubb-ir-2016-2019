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
using LabMppCurseWPF.ViewModels;
using Networking;
using ServicesInterfaces;

namespace LabMppCurseWPF.View
{
	/// <summary>
	/// Interaction logic for ViewLogin.xaml
	/// </summary>
	
	public partial class ViewLogin : Window
	{
		public ViewLogin()
		{
			start();
			InitializeComponent();
		}

		[STAThread]
		public void start()
		{
			BinaryServerFormatterSinkProvider serverProv = new BinaryServerFormatterSinkProvider();
			serverProv.TypeFilterLevel = System.Runtime.Serialization.Formatters.TypeFilterLevel.Full;
			BinaryClientFormatterSinkProvider clientProv = new BinaryClientFormatterSinkProvider();
			IDictionary props = new Hashtable();

			props["port"] = 0;
			TcpChannel channel = new TcpChannel(props, clientProv, serverProv);
			ChannelServices.RegisterChannel(channel, false);
			IServer server =
				(IServer)Activator.GetObject(typeof(IServer), "tcp://192.168.19.1:12345/Curse");
			var service = new ServiceClient(server);
			DataContext = new ViewModelLogin(service);
		}
	}
}
