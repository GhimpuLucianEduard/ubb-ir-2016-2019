using System.Collections.Generic;

namespace AEv2
{
	public class Gena : List<int>
	{
		public Gena()
		{

		}

		public Gena(List<int> list) : base(list)
		{

		}

		public void CreateRandom()
		{
			for (var i = 0; i < Config.MarimeGena; i++)
			{
				Add(Config.RandomGenerator.Next() % 2);
			}
		}

		public override string ToString()
		{
			var genaToShow = "";
			this.ForEach(x => { genaToShow += x.ToString(); });
			return genaToShow;
		}

	}
}