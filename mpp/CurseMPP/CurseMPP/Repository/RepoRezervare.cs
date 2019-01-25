using System.Data;
using CurseMPP.Models;
using CurseMPP.Utils;

namespace CurseMPP.Repository
{
	public class RepoRezervare : AbstractRepoDB<int,Rezervare>
	{
		public RepoRezervare(IValidator<Rezervare> vali) : base(vali)
		{
		}

		public override void delete(int id)
		{
			IDbConnection con = ConnectionFactory.getInstance().createConnection();
			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "delete from Rezervari where id=@id";
				IDbDataParameter paramId = comm.CreateParameter();
				paramId.ParameterName = "@id";
				paramId.Value = id;
				comm.Parameters.Add(paramId);
				var dataR = comm.ExecuteNonQuery();
				if (dataR == 0)
				{
					throw new RepositoryException("Rezervarea nu a fost stearsa!");
				}
				else
				{
					base.delete(id);
				}

			}
			con.Close();
		}

		public override void save(Rezervare entity)
		{
			IDbConnection con = ConnectionFactory.getInstance().createConnection();

			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "insert into Rezervari  values (@id,@idCl,@idCu,@nr)";

				var paramId = comm.CreateParameter();
				paramId.ParameterName = "@id";
				paramId.Value = entity.Id;
				comm.Parameters.Add(paramId);

				var paramIdCl = comm.CreateParameter();
				paramIdCl.ParameterName = "@idCl";
				paramIdCl.Value = entity.idClient;
				comm.Parameters.Add(paramIdCl);

				var paramIdCu = comm.CreateParameter();
				paramIdCu.ParameterName = "@idCu";
				paramIdCu.Value = entity.idCursa;
				comm.Parameters.Add(paramIdCu);

				var paramNr = comm.CreateParameter();
				paramNr.ParameterName = "@nr";
				paramNr.Value = entity.nrLocuri;
				comm.Parameters.Add(paramNr);

				var result = comm.ExecuteNonQuery();
				if (result == 0)
					throw new RepositoryException("Rezervarea nu a fost adaugata!");
				else
				{
					base.save(entity);
				}

			}
			con.Close();
		}

		public override void update(Rezervare entity)
		{
			IDbConnection con = ConnectionFactory.getInstance().createConnection();
			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "update Rezervari " +
								   "set IdClient=@idCl, IdCursa=@idCu, NrLocuri=@nr " +
								   "where Id=@id";


				var paramId = comm.CreateParameter();
				paramId.ParameterName = "@id";
				paramId.Value = entity.Id;
				comm.Parameters.Add(paramId);

				var paramIdCl = comm.CreateParameter();
				paramIdCl.ParameterName = "@idCl";
				paramIdCl.Value = entity.idClient;
				comm.Parameters.Add(paramIdCl);

				var paramIdCu = comm.CreateParameter();
				paramIdCu.ParameterName = "@idCu";
				paramIdCu.Value = entity.idCursa;
				comm.Parameters.Add(paramIdCu);

				var paramNr = comm.CreateParameter();
				paramNr.ParameterName = "@nr";
				paramNr.Value = entity.nrLocuri;
				comm.Parameters.Add(paramNr);

				var dataR = comm.ExecuteNonQuery();
				if (dataR == 0)
				{
					throw new RepositoryException("Rezervarea nu a fost modificata!");
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
				comm.CommandText = "select * from Rezervari";

				using (var dataR = comm.ExecuteReader())
				{
					while (dataR.Read())
					{
						int id = dataR.GetInt32(0);
						int idCl = dataR.GetInt32(1);
						int idCu = dataR.GetInt32(2);
						int nr = dataR.GetInt32(3);
						Rezervare r = new Rezervare(id,idCl,idCu,nr);
						base.save(r);
					}
				}
				con.Close();
			}
		}
	}
}