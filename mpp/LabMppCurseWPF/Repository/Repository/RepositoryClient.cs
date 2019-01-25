using System.Collections.Generic;
using System.Data;
using LabMppCurseWPF.Models;
using LabMppCurseWPF.NET_Edition;

namespace LabMppCurseWPF.Repository
{	
	/// <summary>
	/// Repo Db Client
	/// </summary>
	public class RepositoryClient : IRepository<int,Client>
	{
		private ValidatorClient ValidatorClient = new ValidatorClient();

		public RepositoryClient()
		{
			ValidatorClient = new ValidatorClient();
		}
		
		/// <summary>
		/// Cauta clinet in Db
		/// </summary>
		/// <param name="id">Id-ul clientului de cautat</param>
		/// <returns>Clientul gasit sau null daca nu exista</returns>
		public Client Find(int id)
		{
			var con = SQLiteConnectionFactory.GetInstance().CreateConnection();

			using (var command = con.CreateCommand())
			{
				command.CommandText = "SELECT * " +
				                      "FROM Clienti C " +
				                      "WHERE C.Id = @id";
				var paramId = command.CreateParameter();
				paramId.ParameterName = "@id";
				paramId.Value = id;
				command.Parameters.Add(paramId);

				using (var dataR = command.ExecuteReader())
				{
					if (dataR.Read())
					{
						var idClient = dataR.GetInt32(0);
						var nume = dataR.GetString(1);
						var prenume = dataR.GetString(2);
						var c = new Client(idClient, nume, prenume);
						return c;
					}
				}

			}
			con.Close();
			return null;
		}

		/// <summary>
		/// Find all Clienti din Db
		/// </summary>
		/// <returns>O lista cu toti clienti</returns>
		public IEnumerable<Client> FindAll()
		{
			var con = SQLiteConnectionFactory.GetInstance().CreateConnection();
			var listaClienti = new List<Client>();
			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "select * from Clienti";

				using (var dataR = comm.ExecuteReader())
				{
					while (dataR.Read())
					{
						var id = dataR.GetInt32(0);
						var nume = dataR.GetString(1);
						var prenume = dataR.GetString(2);
						var c = new Client(id,nume,prenume);
						listaClienti.Add(c);
					}
				}
			}
			con.Close();
			return listaClienti;
		}
		
		/// <summary>
		/// Sterge Client cu id dat
		/// Arunca RepositoryException daca operatia a esuat
		/// </summary>
		/// <param name="id"></param>
		public void Delete(int id)
		{
			var con = SQLiteConnectionFactory.GetInstance().CreateConnection();
			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "delete from Clienti where id=@id";
				var paramId = comm.CreateParameter();
				paramId.ParameterName = "@id";
				paramId.Value = id;
				comm.Parameters.Add(paramId);
				var dataR = comm.ExecuteNonQuery();
				if (dataR == 0)
				{
					throw new RepositoryException("Clientul nu a fost sters!");
				}
			}
			con.Close();
		}

		/// <summary>
		/// Adaugare Clinet in Db
		/// Arunca ValidationException daca clientul nu e valid
		/// </summary>
		/// <param name="entity">Clientul de adaugagt</param>
		public void Add(Client entity)
		{
			var con = SQLiteConnectionFactory.GetInstance().CreateConnection();
			ValidatorClient.Validate(entity);
			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "insert into Clienti  values (@id,@nume,@prenume)";
				var paramId = comm.CreateParameter();
				paramId.ParameterName = "@id";
				paramId.Value = entity.Id;
				comm.Parameters.Add(paramId);

				var paramNume = comm.CreateParameter();
				paramNume.ParameterName = "@nume";
				paramNume.Value = entity.Nume;
				comm.Parameters.Add(paramNume);

				var paramPreNume = comm.CreateParameter();
				paramPreNume.ParameterName = "@prenume";
				paramPreNume.Value = entity.Prenume;
				comm.Parameters.Add(paramPreNume);

				var result = comm.ExecuteNonQuery();
				if (result == 0)
					throw new RepositoryException("Clientul nu a fost adaugat!");
			}
			con.Close();
		}

		/// <summary>
		/// Update Client cu noul nou
		/// Arunca ValidationException daca nu e valid
		/// </summary>
		/// <param name="entity">Noul client</param>
		public void Update(Client entity)
		{
			var con = SQLiteConnectionFactory.GetInstance().CreateConnection();
			ValidatorClient.Validate(entity);
			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "update Clienti " +
								   "set Nume=@nume, Prenume=@prenume " +
								   "where Id=@id";

				var paramId = comm.CreateParameter();
				paramId.ParameterName = "@id";
				paramId.Value = entity.Id;
				comm.Parameters.Add(paramId);

				var paramNume = comm.CreateParameter();
				paramNume.ParameterName = "@nume";
				paramNume.Value = entity.Nume;
				comm.Parameters.Add(paramNume);

				var paramPreNume = comm.CreateParameter();
				paramPreNume.ParameterName = "@prenume";
				paramPreNume.Value = entity.Prenume;
				comm.Parameters.Add(paramPreNume);

				var dataR = comm.ExecuteNonQuery();
				if (dataR == 0)
				{
					throw new RepositoryException("Clientul nu a fost modificat!");
				}
			}
			con.Close();
		}

	}
}