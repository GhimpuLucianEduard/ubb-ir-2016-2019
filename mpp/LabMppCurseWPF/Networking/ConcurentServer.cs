using System.Net.Sockets;
using System.Threading;
using System.Xml;

namespace Networking
{	
	/// <summary>
	/// Server concurent
	/// </summary>
	public abstract class ConcurentServer : AbstractServer
	{	
		/// <summary>
		/// Adresa si portul pentru a crea serverul
		/// </summary>
		/// <param name="host"></param>
		/// <param name="port"></param>
		public ConcurentServer(string host, int port) : base(host, port)
		{

		}
		
		/// <summary>
		/// Metoda prin care se deserveste un client
		/// </summary>
		/// <param name="client"></param>
		public override void RequestHandler(TcpClient client)
		{
			var worker = CreateWorker(client);
			worker.Start();
		}

		protected abstract Thread CreateWorker(TcpClient client);

	}
}