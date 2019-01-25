using System.Collections.Generic;
using System.Linq;
using Domain.Models;
using Domain.Repository;

namespace Server.Service
{
	public class JucatorService
	{
		private JucatorRepository _repository;

		public JucatorService()
		{
			_repository = new JucatorRepository();
		}

		public Jucator HandleLogin(Jucator player)
		{
			return _repository.GetAll().ToList().FirstOrDefault((x => x.Parola.Equals(player.Parola) && x.Username.Equals(player.Username)));
		}

		public List<Jucator> GetAll()
		{
			return _repository.GetAll().ToList();
		}
	}
}