using System;
using System.Collections.Generic;
using System.Data;
using CurseMPP.Models;
using CurseMPP.Utils;

namespace CurseMPP.Repository
{
	public class RepoDestinatii : AbstractRepoDB<int,Destinatie>
	{


		public override void delete(int id)
		{
			IDbConnection con = ConnectionFactory.getInstance().createConnection();
			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "delete from Destinatii where id=@id";
				IDbDataParameter paramId = comm.CreateParameter();
				paramId.ParameterName = "@id";
				paramId.Value = id;
				comm.Parameters.Add(paramId);
				var dataR = comm.ExecuteNonQuery();
				if (dataR == 0)
				{
					throw new RepositoryException("Destinatia nu a fost stearsa!");
				}
				else
				{
					base.delete(id);
				}
					
			}
			con.Close();
		}

		public override void save(Destinatie entity)
		{
			IDbConnection con = ConnectionFactory.getInstance().createConnection();

			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "insert into Destinatii  values (@id,@nume)";
				var paramId = comm.CreateParameter();
				paramId.ParameterName = "@id";
				paramId.Value = entity.Id;
				comm.Parameters.Add(paramId);

				var paramNume = comm.CreateParameter();
				paramNume.ParameterName = "@nume";
				paramNume.Value = entity.nume;
				comm.Parameters.Add(paramNume);

				var result = comm.ExecuteNonQuery();
				if (result == 0)
					throw new RepositoryException("Destinatia nu a fost adaugata!");
				else
				{
					base.save(entity);
				}
				
			}
			con.Close();
		}

		public override void update(Destinatie entity)
		{
			IDbConnection con = ConnectionFactory.getInstance().createConnection();
			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "update Destinatii " +
				                   "set Nume=@nume "+
				                   "where Id=@id";


				IDbDataParameter paramId = comm.CreateParameter();
				paramId.ParameterName = "@id";
				paramId.Value = entity.Id;
				comm.Parameters.Add(paramId);

				IDbDataParameter paramNume = comm.CreateParameter();
				paramNume.ParameterName = "@nume";
				paramNume.Value = entity.nume;
				comm.Parameters.Add(paramNume);

				var dataR = comm.ExecuteNonQuery();
				if (dataR == 0)
				{
					throw new RepositoryException("Destinatia nu a fost modificata!");
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
				comm.CommandText = "select * from Destinatii";

				using (var dataR = comm.ExecuteReader())
				{
					while (dataR.Read())
					{
						int id = dataR.GetInt32(0);
						string nume = dataR.GetString(1);
						Destinatie d = new Destinatie(id, nume);
						base.save(d);
					}
				}
				con.Close();
			}
		}

		public RepoDestinatii(IValidator<Destinatie> vali) : base(vali)
		{
		}
	}

		

	
}