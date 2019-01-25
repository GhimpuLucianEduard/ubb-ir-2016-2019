using System.Collections.Generic;
using LabMppCurseWPF.Models;
using LabMppCurseWPF.NET_Edition;
using LabMppCurseWPF.Repository;

namespace LabMppCurseWPF.Service
{	
	/// <summary>
	/// Service cu operatii de baza pentru Client
	/// </summary>
	internal static class ServiceClienti
	{	
		/// <summary>
		/// Repository cu operatii CRUD pentru Client
		/// </summary>
		private static IRepository<int,Client> _repository = new RepositoryClient();

		public static Client FindClient(int id)
		{
			return _repository.Find(id);
		}

		public static Client FindClinetDupaDetalii(string nume, string prenume)
		{
			var con = SQLiteConnectionFactory.GetInstance().CreateConnection();

			using (var command = con.CreateCommand())
			{
				command.CommandText = "SELECT * " +
				                      "FROM Clienti C " +
				                      "WHERE C.Nume=@Nume and C.Prenume=@Prenume";

				var paramNume = command.CreateParameter();
				paramNume.ParameterName = "@Nume";
				paramNume.Value = nume;
				command.Parameters.Add(paramNume);

				var paramPrenume = command.CreateParameter();
				paramPrenume.ParameterName = "@Prenume";
				paramPrenume.Value = prenume;
				command.Parameters.Add(paramPrenume);

				using (var dataR = command.ExecuteReader())
				{
					if (dataR.Read())
					{
						var idClient = dataR.GetInt32(0);
						var numeC = dataR.GetString(1);
						var prenumeC = dataR.GetString(2);
						var c = new Client(idClient, numeC, prenumeC);
						return c;
					}
				}

			}
			con.Close();
			return null;
		}

		public static IEnumerable<Client> FindAllClienti()
		{
			return _repository.FindAll();
		}

		public static void addClient(Client c)
		{
			_repository.Add(c);
		}

	}
}