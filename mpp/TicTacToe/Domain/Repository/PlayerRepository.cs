using System.Collections.Generic;
using System.Linq;
using Domain.Models;

namespace Domain.Repository
{
	public class PlayerRepository
	{
		public Player Find(int id)
		{
			using (var context = new TicTacToeModels())
			{
				return context.Players.Find(id);
			}
		}

		public IEnumerable<Player> GetAll()
		{
			using (var context = new TicTacToeModels())
			{
				return context.Players.ToList();
			}
		}
	}
}