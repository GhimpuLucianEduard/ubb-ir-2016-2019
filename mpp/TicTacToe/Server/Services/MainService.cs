using System;
using System.Collections.Generic;
using Domain.Models;
using Domain.Repository;
using System.Linq;

namespace Server.Services
{
	public class MainService
	{
		private PlayerRepository _playerRepository;
		private GameRepository _gameRepository;

		

		public MainService()
		{
			_playerRepository = new PlayerRepository();
			_gameRepository = new GameRepository();
		}

		public Player HandleLogin(Player player)
		{
			return _playerRepository.GetAll().ToList().FirstOrDefault((x => x.Pass.Equals(player.Pass) && x.Username.Equals(player.Username)));
		}
	}
}