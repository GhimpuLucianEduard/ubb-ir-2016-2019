using Domain.Models;

namespace Interfaces
{
	public interface IServer
	{
		void Login(Jucator player, IObserver client);
		void LogOut(string playerUsername, IObserver clientService);
	}
}