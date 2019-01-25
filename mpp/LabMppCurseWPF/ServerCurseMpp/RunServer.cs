using System;
using System.Collections;
using System.Collections.Generic;
using System.Runtime.Remoting;
using System.Runtime.Remoting.Channels;
using System.Runtime.Remoting.Channels.Tcp;
using System.Runtime.Remoting.Services;
using ServicesInterfaces;

namespace ServerCurseMpp
{
	public class RunServer
	{
		public static void Main(string[] args)
		{
			BinaryServerFormatterSinkProvider serverProv = new BinaryServerFormatterSinkProvider();
			serverProv.TypeFilterLevel = System.Runtime.Serialization.Formatters.TypeFilterLevel.Full;
			BinaryClientFormatterSinkProvider clientProv = new BinaryClientFormatterSinkProvider();
			var props = new Hashtable();
			props["port"] = 12345;

			var channel = new TcpChannel(props, clientProv, serverProv);
			ChannelServices.RegisterChannel(channel, false);

			var service = new ServerService();
			RemotingServices.Marshal(service, "Curse");
			Console.WriteLine("Server started ...");
			Console.WriteLine("Press <enter> to exit...");
//			var count = service.GetAllDestinatii().Length;
//			Console.WriteLine(count);
			Console.ReadLine();
		}
	}
}