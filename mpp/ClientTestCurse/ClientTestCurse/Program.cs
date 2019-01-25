using System;
using System.Linq;
using System.Threading.Tasks;

namespace ClientTestCurse
{
	class Program
	{
		static void Main(string[] args)
		{
			var test = new TestServiceConsumer();

			Task.Run(async () => await test.GetAll()).Wait();
			Task.Run(async () => await test.Delete(test.Dictionary.Last().Value.Id)).Wait();
			Task.Run(async () => await test.GetAll()).Wait();
			var newCursa = new Cursa()
			{
				DataPlecare = DateTime.Now,
				IdDestinatie = 1,
				LocPlecare = "Galati",
				NrLocuriDisponibile = 10
			};
			Task.Run(async () => await test.Add(newCursa)).Wait();
			Task.Run(async () => await test.GetAll()).Wait();
			var deUp = test.Dictionary.Last().Value;
			deUp.LocPlecare = "UPDATE";
			deUp.DataPlecare = DateTime.Now;
			Task.Run(async () => await test.Update(deUp)).Wait();
			Task.Run(async () => await test.GetAll()).Wait();
			Console.ReadKey();
		}
	}
}
