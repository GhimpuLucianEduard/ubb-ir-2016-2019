using LabMppCurseWPF.Models;

namespace Services
{
	/// <summary>
	/// Interfata pentru o clasa observabila
	/// de tip server care sa notifice toti 
	/// </summary>
	public interface IServerObservable
	{
		void Login(Personal personal, IClientObserver client);
	}
}