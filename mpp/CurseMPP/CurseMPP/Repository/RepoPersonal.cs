using System.Data;
using CurseMPP.Models;
using CurseMPP.Utils;

namespace CurseMPP.Repository
{
	public class RepoPersonal : AbstractRepoDB<int,Personal>
	{
		public override void delete(int id)
		{
			IDbConnection con = ConnectionFactory.getInstance().createConnection();
			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "delete from Personal where id=@id";
				IDbDataParameter paramId = comm.CreateParameter();
				paramId.ParameterName = "@id";
				paramId.Value = id;
				comm.Parameters.Add(paramId);
				var dataR = comm.ExecuteNonQuery();
				if (dataR == 0)
				{
					throw new RepositoryException("Personalul nu a fost stears!");
				}
				else
				{
					base.delete(id);
				}

			}
			con.Close();
		}

		public override void save(Personal entity)
		{
			IDbConnection con = ConnectionFactory.getInstance().createConnection();

			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "insert into Personal values (@id, @userName, @password, @nume, @prenume)";

				var paramId = comm.CreateParameter();
				paramId.ParameterName = "@id";
				paramId.Value = entity.Id;
				comm.Parameters.Add(paramId);

				var paramUser = comm.CreateParameter();
				paramUser.ParameterName = "@userName";
				paramUser.Value = entity.userName;
				comm.Parameters.Add(paramUser);

				var paramPass = comm.CreateParameter();
				paramPass.ParameterName = "@password";
				paramPass.Value = entity.password;
				comm.Parameters.Add(paramPass);

				var paramNume = comm.CreateParameter();
				paramNume.ParameterName = "@nume";
				paramNume.Value = entity.nume;
				comm.Parameters.Add(paramNume);

				var paramPrenume = comm.CreateParameter();
				paramPrenume.ParameterName = "@prenume";
				paramPrenume.Value = entity.prenume;
				comm.Parameters.Add(paramPrenume);

				var result = comm.ExecuteNonQuery();
				if (result == 0)
					throw new RepositoryException("Personalul nu a fost adaugata!");
				else
				{
					base.save(entity);
				}

			}
			con.Close();
		}

		public override void update(Personal entity)
		{
			IDbConnection con = ConnectionFactory.getInstance().createConnection();
			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "update Personal " +
								   "set UserName=@userName, Password=@password, Nume=@nume, Prenume=@prenume " +
								   "where Id=@id";


				var paramId = comm.CreateParameter();
				paramId.ParameterName = "@id";
				paramId.Value = entity.Id;
				comm.Parameters.Add(paramId);

				var paramUser = comm.CreateParameter();
				paramUser.ParameterName = "@userName";
				paramUser.Value = entity.userName;
				comm.Parameters.Add(paramUser);

				var paramPass = comm.CreateParameter();
				paramPass.ParameterName = "@password";
				paramPass.Value = entity.password;
				comm.Parameters.Add(paramPass);

				var paramNume = comm.CreateParameter();
				paramNume.ParameterName = "@nume";
				paramNume.Value = entity.nume;
				comm.Parameters.Add(paramNume);

				var paramPrenume = comm.CreateParameter();
				paramPrenume.ParameterName = "@prenume";
				paramPrenume.Value = entity.prenume;
				comm.Parameters.Add(paramPrenume);

				var dataR = comm.ExecuteNonQuery();
				if (dataR == 0)
				{
					throw new RepositoryException("Personal nu a fost modificat!");
				}
				else
				{
					base.update(entity);
				}

			}
			con.Close();
		}

		public override void loadData()
		{
			IDbConnection con = ConnectionFactory.getInstance().createConnection();

			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "select * from Personal";

				using (var dataR = comm.ExecuteReader())
				{
					while (dataR.Read())
					{
						int id = dataR.GetInt32(0);
						string user = dataR.GetString(1);
						string pass = dataR.GetString(2);
						string nume = dataR.GetString(3);
						string prenume = dataR.GetString(4);
						Personal p = new Personal(id,user,pass,nume,prenume);
						base.save(p);
					}
				}
				con.Close();
			}
		}

		public RepoPersonal(IValidator<Personal> vali) : base(vali)
		{
			
		}
	}
}