using System;
using System.Data;
using CurseMPP.Models;
using CurseMPP.Utils;

namespace CurseMPP.Repository
{
	public class RepoCurse : AbstractRepoDB<int, Cursa>
	{
		public RepoCurse(IValidator<Cursa> vali) : base(vali)
		{
		}

		public override void loadData()
		{
			IDbConnection con = ConnectionFactory.getInstance().createConnection();

			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "select * from Curse";

				using (var dataR = comm.ExecuteReader())
				{
					while (dataR.Read())
					{
						int id = dataR.GetInt32(0);
						int destinatie = dataR.GetInt32(1);
						DateTime data = dataR.GetDateTime(2);
						int nrLocuri = dataR.GetInt32(3);
						Cursa c = new Cursa(id,destinatie,data,nrLocuri);
						base.save(c);
					}
				}

			}	
			con.Close();
		}

		public override void delete(int id)
		{
			IDbConnection con = ConnectionFactory.getInstance().createConnection();
			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "delete from Curse where id=@id";
				IDbDataParameter paramId = comm.CreateParameter();
				paramId.ParameterName = "@id";
				paramId.Value = id;
				comm.Parameters.Add(paramId);
				var dataR = comm.ExecuteNonQuery();
				if (dataR == 0)
				{
					throw new RepositoryException("Cursa nu a fost stearsa!");
				}
				else
				{
					base.delete(id);
				}

			}
			con.Close();
		}

		public override void save(Cursa entity)
		{
			IDbConnection con = ConnectionFactory.getInstance().createConnection();

			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "insert into Curse  values (@id, @destinatie, @dataPlecare, @nrLocuri)";

				var paramId = comm.CreateParameter();
				paramId.ParameterName = "@id";
				paramId.Value = entity.Id;
				comm.Parameters.Add(paramId);

				var paramDestinatie = comm.CreateParameter();
				paramDestinatie.ParameterName = "@destinatie";
				paramDestinatie.Value = entity.idDestinatie;
				comm.Parameters.Add(paramDestinatie);

				var paramData = comm.CreateParameter();
				paramData.ParameterName = "@dataPlecare";
				paramData.Value = entity.dataTimePlecare;
				comm.Parameters.Add(paramData);

				var paramLocuri = comm.CreateParameter();
				paramLocuri.ParameterName = "@nrLocuri";
				paramLocuri.Value = entity.nrLocuriDisponibile;
				comm.Parameters.Add(paramLocuri);

				var result = comm.ExecuteNonQuery();
				if (result == 0)
					throw new RepositoryException("Cursa nu a fost adaugata!");
				else
				{
					base.save(entity);
				}

			}
		}

		public override void update(Cursa entity)
		{
			IDbConnection con = ConnectionFactory.getInstance().createConnection();
			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "update Curse " +
				                   "set Destinatie=@Destinatie, DataPlecare=@dataPlecare, NrLocuri=@nrLocuri " +
				                   "where Id=@id";


				var paramId = comm.CreateParameter();
				paramId.ParameterName = "@id";
				paramId.Value = entity.Id;
				comm.Parameters.Add(paramId);

				var paramDestinatie = comm.CreateParameter();
				paramDestinatie.ParameterName = "@destinatie";
				paramDestinatie.Value = entity.idDestinatie;
				comm.Parameters.Add(paramDestinatie);

				var paramData = comm.CreateParameter();
				paramData.ParameterName = "@dataPlecare";
				paramData.Value = entity.dataTimePlecare;
				comm.Parameters.Add(paramData);

				var paramLocuri = comm.CreateParameter();
				paramLocuri.ParameterName = "@nrLocuri";
				paramLocuri.Value = entity.nrLocuriDisponibile;
				comm.Parameters.Add(paramLocuri);

				var dataR = comm.ExecuteNonQuery();
				if (dataR == 0)
				{
					throw new RepositoryException("Cursa nu a fost modificata!");
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