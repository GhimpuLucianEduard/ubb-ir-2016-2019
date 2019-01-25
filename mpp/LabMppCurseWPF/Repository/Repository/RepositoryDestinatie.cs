using System.Collections.Generic;
using System.Data;
using LabMppCurseWPF.Models;
using LabMppCurseWPF.NET_Edition;

namespace LabMppCurseWPF.Repository
{
	public class RepositoryDestinatie : IRepository<int,Destinatie>
	{
		private ValidatorDestinatie ValidatorDestinatie;

		public RepositoryDestinatie()
		{
			ValidatorDestinatie = new ValidatorDestinatie();
		}


		/// <summary>
		/// Delete Destinatie from Db
		/// Arunca RepositoryException in caz de esec
		/// </summary>
		/// <param name="id">Id-ul destinatie de sters</param>
		public void Delete(int id)
		{
			var con = SQLiteConnectionFactory.GetInstance().CreateConnection();
			
			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "delete from Destinatii where id=@id";
				var paramId = comm.CreateParameter();
				paramId.ParameterName = "@id";
				paramId.Value = id;
				comm.Parameters.Add(paramId);
				var dataR = comm.ExecuteNonQuery();
				if (dataR == 0)
				{
					throw new RepositoryException("Destinatia nu a fost stearsa!");
				}				
			}
			con.Close();
		}

		/// <summary>
		/// Add Destinatie to DB
		/// Arunca ValidationExecption caz de esec
		/// </summary>
		/// <param name="entity">Destinatia de adaugat</param>
		public void Add(Destinatie entity)
		{
			var con = SQLiteConnectionFactory.GetInstance().CreateConnection();
			ValidatorDestinatie.Validate(entity);
			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "insert into Destinatii values (@id,@nume)";
				var paramId = comm.CreateParameter();
				paramId.ParameterName = "@id";
				paramId.Value = entity.Id;
				comm.Parameters.Add(paramId);

				var paramNume = comm.CreateParameter();
				paramNume.ParameterName = "@nume";
				paramNume.Value = entity.Nume;
				comm.Parameters.Add(paramNume);

				var result = comm.ExecuteNonQuery();
				if (result == 0)
					throw new RepositoryException("Destinatia nu a fost adaugata!");
			}
			con.Close();
		}
			
		/// <summary>
		/// Update Destinatie
		/// Arunca RepositoryException in caz de esec
		/// </summary>
		/// <param name="entity">Noua destinatie</param>
		public void Update(Destinatie entity)
		{
			var con = SQLiteConnectionFactory.GetInstance().CreateConnection();
			ValidatorDestinatie.Validate(entity);
			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "update Destinatii " +
				                   "set Nume=@nume "+
				                   "where Id=@id";


				var paramId = comm.CreateParameter();
				paramId.ParameterName = "@id";
				paramId.Value = entity.Id;
				comm.Parameters.Add(paramId);

				var paramNume = comm.CreateParameter();
				paramNume.ParameterName = "@nume";
				paramNume.Value = entity.Nume;
				comm.Parameters.Add(paramNume);

				var dataR = comm.ExecuteNonQuery();
				if (dataR == 0)
				{
					throw new RepositoryException("Destinatia nu a fost modificata!");
				}
			}
			con.Close();
		}

		/// <summary>
		/// Find Destinatie from Db by Id
		/// </summary>
		/// <param name="id">Id-ul destiantiei de gasit</param>
		/// <returns>Destinatia gasita sau null daca nu exista in Db</returns>
		public Destinatie Find(int id)
		{
			var con = SQLiteConnectionFactory.GetInstance().CreateConnection();

			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "select * from Destinatii where Id=@id";

				var paramId = comm.CreateParameter();
				paramId.ParameterName = "@id";
				paramId.Value = id;
				comm.Parameters.Add(paramId);

				using (var dataR = comm.ExecuteReader())
				{
					while (dataR.Read())
					{
						var idDestinatie = dataR.GetInt32(0);
						var nume = dataR.GetString(1);
						var d = new Destinatie(idDestinatie, nume);
						return d;
					}
				}
			}
			con.Close();
			return null;
		}

		/// <summary>
		/// Find all Destinatii din Db
		/// </summary>
		/// <returns>Lista cu toate destinatiile</returns>
		public IEnumerable<Destinatie> FindAll()
		{
			var con = SQLiteConnectionFactory.GetInstance().CreateConnection();
			var listaDestinatii = new List<Destinatie>();

			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "select * from Destinatii";

				using (var dataR = comm.ExecuteReader())
				{
					while (dataR.Read())
					{
						var id = dataR.GetInt32(0);
						var nume = dataR.GetString(1);
						var d = new Destinatie(id, nume);
						listaDestinatii.Add(d);
					
					}
				}
			}
			con.Close();
			return listaDestinatii;
		}
	}

		

	
}