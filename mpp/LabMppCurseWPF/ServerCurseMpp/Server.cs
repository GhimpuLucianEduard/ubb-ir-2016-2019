using System;
using System.Net.Sockets;
using System.Threading;
using Networking;
using ServicesInterfaces;

namespace ServerCurseMpp
{
	public class Server : ConcurentServer
	{
		private IServer _server;
		private Worker _worker;
		public Server(string host, int port, IServer server) : base(host, port)
		{
			_server = server;
			Console.WriteLine("Server started...");
		}

		protected override Thread CreateWorker(TcpClient client)
		{
			_worker = new Worker(_server, client);
			return new Thread(new ThreadStart(_worker.Run));
		}
	}
}