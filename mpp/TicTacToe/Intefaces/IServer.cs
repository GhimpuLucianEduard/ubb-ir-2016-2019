using Domain.Models;

namespace Intefaces
{
	public interface IServer
	{
		void Login(Player player, IObserver client);
		void HandleStartJoc(Player player, IObserver client);
		void HandleMutare(Player player, IObserver client, int x, int y, string semn);
	}
}