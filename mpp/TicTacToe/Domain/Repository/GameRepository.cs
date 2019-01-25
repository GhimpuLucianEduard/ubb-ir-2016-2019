using System.Collections.Generic;
using System.Linq;
using Domain.Models;

namespace Domain.Repository
{
	public class GameRepository
	{
		public void Add(Game entity)
		{
			using (var context = new TicTacToeModels())
			{
				context.Games.Add(entity);
				context.SaveChanges();
			}
		}

		public Game Find(int id)
		{
			using (var context = new TicTacToeModels())
			{
				return context.Games.Find(id);
			}
		}

		public IEnumerable<Game> GetAll()
		{
			using (var context = new TicTacToeModels())
			{
				return context.Games.ToList();
			}
		}

		public void Update(Game entity)
		{
			using (var context = new TicTacToeModels())
			{
				var oldEntity = Find(entity.Id);
				context.Entry(oldEntity).CurrentValues.SetValues(entity);
				context.SaveChanges();
			}
		}
	}
}