using System.Collections.Generic;
using System.Linq;
using Domain.Models;
using Domain.Repository;
using REST.Models;

namespace REST.Service
{
	public class GameService
	{
		private RazboiEntities JocService;

		public GameService()
		{
			JocService = new RazboiEntities();
		}

		public Game GetJoc(int id)
		{
			var joc = JocService.Jocs.Find(id);
			var listP = new List<string>();
			joc.Participares.ToList().ForEach(x =>
			{
				listP.Add(x.Jucator.Username);
			});
			return new Game(joc.Jucator.Username, joc.Punctaj, listP);
		}

	}
}