using System;
using System.Net;
using System.Net.Sockets;

namespace Networking
{	
	/// <summary>
	/// Clasa abstracta care reprezinta un server TCP
	/// </summary>
	public abstract class AbstractServer
	{
		private TcpListener _server;
		private string _host;
		private int _port;

		public AbstractServer(string host, int port)
		{
			_host = host;
			_port = port;
		}

		/// <summary>
		/// Metoda prin care serverul
		/// incepe sa asculte si sa astepte requesturi
		/// </summary>
		public void StartServer()
		{	
			//Creaza tot ce e nevoie
			//Asozia adresa si portul unui nou server TCP
			var adress = IPAddress.Parse(_host);
			var endPoint = new IPEndPoint(adress, _port);
			_server = new TcpListener(endPoint);
			_server.Start();

			while (true)
			{
				Console.WriteLine("Waiting for clients...");

				var client = _server.AcceptTcpClient();
				Console.WriteLine("Clinet connected: " + client.ToString());
				RequestHandler(client);
			}
		}

		/// <summary>
		/// metoda prin care se deserveste un client
		/// </summary>
		/// <param name="client">clinetul de deservit</param>
		public abstract void RequestHandler(TcpClient client);
	}
}