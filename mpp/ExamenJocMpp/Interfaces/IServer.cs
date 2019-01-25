using System;
using Domain.Models;

namespace Interfaces
{
	public interface IServer
	{
		void Login(Jucator player, IObserver client);
		void LogOut(string playerUsername, IObserver clientService);
		void HandleStartJoc(Jucator player, IObserver client);
		void HandleMutareNoua(Jucator player, IObserver client, int numarGenerat);
	}
}
