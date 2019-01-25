using System.Collections.Generic;
using System.Data;
using CurseMPP.Models;
using LabMppCurseWPF.Models;
using LabMppCurseWPF.NET_Edition;

namespace LabMppCurseWPF.Repository
{
	public class RepositoryRezervare : IRepository<int,Rezervare>
	{
		private ValidatorRezervare ValidatorRezervare;

		public RepositoryRezervare()
		{
			ValidatorRezervare = new ValidatorRezervare();
		}
		
		/// <summary>
		/// Delete Rezervare from Db
		/// Arunca RepositoryExecption in caz de esec
		/// </summary>
		/// <param name="id">Id-ul rezervarii de sters</param>
		public void Delete(int id)
		{
			var con = SQLiteConnectionFactory.GetInstance().CreateConnection();

			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "delete from Rezervari where id=@id";
				var paramId = comm.CreateParameter();
				paramId.ParameterName = "@id";
				paramId.Value = id;
				comm.Parameters.Add(paramId);
				var dataR = comm.ExecuteNonQuery();
				if (dataR == 0)
				{
					throw new RepositoryException("Rezervarea nu a fost stearsa!");
				}
			}
			con.Close();
		}

		/// <summary>
		/// Add Rezervare in Db
		/// Arunca RepositoryExecption in caz de esec
		/// </summary>
		/// <param name="entity">Rezervare de adaugat</param>
		public void Add(Rezervare entity)
		{
			var con = SQLiteConnectionFactory.GetInstance().CreateConnection();
			ValidatorRezervare.Validate(entity);
			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "insert into Rezervari  values (@id,@idCl,@idCu,@nr)";

				var paramId = comm.CreateParameter();
				paramId.ParameterName = "@id";
				paramId.Value = entity.Id;
				comm.Parameters.Add(paramId);

				var paramIdCl = comm.CreateParameter();
				paramIdCl.ParameterName = "@idCl";
				paramIdCl.Value = entity.IdClient;
				comm.Parameters.Add(paramIdCl);

				var paramIdCu = comm.CreateParameter();
				paramIdCu.ParameterName = "@idCu";
				paramIdCu.Value = entity.IdCursa;
				comm.Parameters.Add(paramIdCu);

				var paramNr = comm.CreateParameter();
				paramNr.ParameterName = "@nr";
				paramNr.Value = entity.NrLocuri;
				comm.Parameters.Add(paramNr);

				var result = comm.ExecuteNonQuery();
				if (result == 0)
					throw new RepositoryException("Rezervarea nu a fost adaugata!");

			}
			con.Close();
		}

		/// <summary>
		/// Modifica o rezervare
		/// Arunca RepositoryExceptiom in caz de esec
		/// </summary>
		/// <param name="entity">Rezervarea modificata</param>
		public void Update(Rezervare entity)
		{
			var con = SQLiteConnectionFactory.GetInstance().CreateConnection();
			ValidatorRezervare.Validate(entity);
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
				paramIdCl.Value = entity.IdClient;
				comm.Parameters.Add(paramIdCl);

				var paramIdCu = comm.CreateParameter();
				paramIdCu.ParameterName = "@idCu";
				paramIdCu.Value = entity.IdCursa;
				comm.Parameters.Add(paramIdCu);

				var paramNr = comm.CreateParameter();
				paramNr.ParameterName = "@nr";
				paramNr.Value = entity.NrLocuri;
				comm.Parameters.Add(paramNr);

				var dataR = comm.ExecuteNonQuery();
				if (dataR == 0)
				{
					throw new RepositoryException("Rezervarea nu a fost modificata!");
				}
			}
			con.Close();
		}

		/// <summary>
		/// Find all Rezervari din Db
		/// </summary>
		/// <returns>Lista cu toate Rezervarile</returns>
		public IEnumerable<Rezervare> FindAll()
		{
			var con = SQLiteConnectionFactory.GetInstance().CreateConnection();
			var listaRezervari = new List<Rezervare>();
			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "select * from Rezervari";

				using (var dataR = comm.ExecuteReader())
				{
					while (dataR.Read())
					{
						var id = dataR.GetInt32(0);
						var idCl = dataR.GetInt32(1);
						var idCu = dataR.GetInt32(2);
						var nr = dataR.GetInt32(3);
						var r = new Rezervare(id,idCl,idCu,nr);
						listaRezervari.Add(r);
					}
				}
			}
			con.Close();
			return listaRezervari;
		}

		public Rezervare Find(int id)
		{
			var con = SQLiteConnectionFactory.GetInstance().CreateConnection();
			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "select * from Rezervari";

				var paramId = comm.CreateParameter();
				paramId.ParameterName = "@id";
				paramId.Value = id;
				comm.Parameters.Add(paramId);
				
				using (var dataR = comm.ExecuteReader())
				{
					while (dataR.Read())
					{
						var idRezervare = dataR.GetInt32(0);
						var idCl = dataR.GetInt32(1);
						var idCu = dataR.GetInt32(2);
						var nr = dataR.GetInt32(3);
						var r = new Rezervare(idRezervare, idCl, idCu, nr);
						return r;
					}
				}
			}
			con.Close();
			return null;
		}
	}
}