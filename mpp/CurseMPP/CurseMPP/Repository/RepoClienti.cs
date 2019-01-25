using System.Data;
using CurseMPP.Models;
using CurseMPP.Utils;

namespace CurseMPP.Repository
{
	public class RepoClienti : AbstractRepoDB<int,Client>
	{
		public RepoClienti(IValidator<Client> vali) : base(vali)
		{
		}

		public override void loadData()
		{
			IDbConnection con = ConnectionFactory.getInstance().createConnection();

			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "select * from Clienti";

				using (var dataR = comm.ExecuteReader())
				{
					while (dataR.Read())
					{
						int id = dataR.GetInt32(0);
						string nume = dataR.GetString(1);
						string prenume = dataR.GetString(2);
						Client d = new Client(id,nume,prenume);
						base.save(d);
					}
				}
				con.Close();
			}
		}

		public override void delete(int id)
		{
			IDbConnection con = ConnectionFactory.getInstance().createConnection();
			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "delete from Clienti where id=@id";
				IDbDataParameter paramId = comm.CreateParameter();
				paramId.ParameterName = "@id";
				paramId.Value = id;
				comm.Parameters.Add(paramId);
				var dataR = comm.ExecuteNonQuery();
				if (dataR == 0)
				{
					throw new RepositoryException("Client nu a fost stears!");
				}
				else
				{
					base.delete(id);
				}

			}
			con.Close();
		}

		public override void save(Client entity)
		{
			IDbConnection con = ConnectionFactory.getInstance().createConnection();

			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "insert into Clienti  values (@id,@nume,@prenume)";
				var paramId = comm.CreateParameter();
				paramId.ParameterName = "@id";
				paramId.Value = entity.Id;
				comm.Parameters.Add(paramId);

				var paramNume = comm.CreateParameter();
				paramNume.ParameterName = "@nume";
				paramNume.Value = entity.nume;
				comm.Parameters.Add(paramNume);

				var paramPreNume = comm.CreateParameter();
				paramPreNume.ParameterName = "@prenume";
				paramPreNume.Value = entity.nume;
				comm.Parameters.Add(paramPreNume);

				var result = comm.ExecuteNonQuery();
				if (result == 0)
					throw new RepositoryException("Clientul nu a fost adaugat!");
				else
				{
					base.save(entity);
				}

			}
			con.Close();
		}

		public override void update(Client entity)
		{
			IDbConnection con = ConnectionFactory.getInstance().createConnection();
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
				paramNume.Value = entity.nume;
				comm.Parameters.Add(paramNume);

				var paramPreNume = comm.CreateParameter();
				paramPreNume.ParameterName = "@prenume";
				paramPreNume.Value = entity.nume;
				comm.Parameters.Add(paramPreNume);

				var dataR = comm.ExecuteNonQuery();
				if (dataR == 0)
				{
					throw new RepositoryException("Clientul nu a fost modificat!");
				}
				else
				{
					base.update(entity);
				}

			}
			con.Close();
		}

	}
}