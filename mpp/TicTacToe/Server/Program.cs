using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Remoting;
using System.Runtime.Remoting.Channels;
using System.Runtime.Remoting.Channels.Tcp;
using System.Text;
using System.Threading.Tasks;

namespace Server
{
	class Program
	{
		static void Main(string[] args)
		{
			BinaryServerFormatterSinkProvider serverProv = new BinaryServerFormatterSinkProvider();
			serverProv.TypeFilterLevel = System.Runtime.Serialization.Formatters.TypeFilterLevel.Full;
			BinaryClientFormatterSinkProvider clientProv = new BinaryClientFormatterSinkProvider();
			var props = new Hashtable();
			props["port"] = 3291;

			var channel = new TcpChannel(props, clientProv, serverProv);
			ChannelServices.RegisterChannel(channel, false);

			var service = new ServerService();
			RemotingServices.Marshal(service, "Curse");
			Console.WriteLine("Server started ...");
			Console.WriteLine("Press <enter> to exit...");
			Console.ReadLine();
		}
	}
}
