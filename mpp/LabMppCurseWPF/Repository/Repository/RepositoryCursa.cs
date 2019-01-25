using System;
using System.Collections.Generic;
using System.Data;
using CurseMPP.Models;
using LabMppCurseWPF.Models;
using LabMppCurseWPF.NET_Edition;

namespace LabMppCurseWPF.Repository
{	
	/// <summary>
	/// Repository Db pentur Cursa
	/// </summary>
	public class RepositoryCursa : IRepository<int,Cursa>
	{
		private ValidatorCursa ValidatorCursa;

		public RepositoryCursa()
		{
			ValidatorCursa = new ValidatorCursa();
		}

		/// <summary>
		/// Find all Curse from Db
		/// </summary>
		/// <returns>Lista cu toate cursele din Db</returns>
		public IEnumerable<Cursa> FindAll()
		{
			var con = SQLiteConnectionFactory.GetInstance().CreateConnection();
			var listaCurse = new List<Cursa>();

			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "select * from Curse";
				using (var dataR = comm.ExecuteReader())
				{
					while (dataR.Read())
					{
						var id = dataR.GetInt32(0);
						var destinatie = dataR.GetInt32(1);
						var data = dataR.GetDateTime(2);
						var nrLocuri = dataR.GetInt32(3);
						var plecare = dataR.GetString(4);
						var c = new Cursa(id,destinatie,data,nrLocuri,plecare);
						listaCurse.Add(c);
					}
				}

			}	
			con.Close();
			return listaCurse;
		}

		/// <summary>
		/// Delete Cursa from Db
		/// Arunca RepositoryExecption in caz de esec
		/// </summary>
		/// <param name="id">Id-ul cursei de sters</param>
		public void Delete(int id)
		{
			var con = SQLiteConnectionFactory.GetInstance().CreateConnection();
			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "delete from Curse where id=@id";
				var paramId = comm.CreateParameter();
				paramId.ParameterName = "@id";
				paramId.Value = id;
				comm.Parameters.Add(paramId);
				var dataR = comm.ExecuteNonQuery();
				if (dataR == 0)
				{
					throw new RepositoryException("Cursa nu a fost stearsa!");
				}
			}
			con.Close();
		}

		/// <summary>
		/// Adauga Cursa in Db
		/// Arunca RepositoryException in caz de esec
		/// </summary>
		/// <param name="entity">Cursa e adaugat</param>
		public void Add(Cursa entity)
		{
			var con = SQLiteConnectionFactory.GetInstance().CreateConnection();
			ValidatorCursa.Validate(entity);

			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "insert into Curse  values (@id, @destinatie, @dataPlecare, @nrLocuri, @plecare)";

				var paramId = comm.CreateParameter();
				paramId.ParameterName = "@id";
				paramId.Value = entity.Id;
				comm.Parameters.Add(paramId);

				var paramDestinatie = comm.CreateParameter();
				paramDestinatie.ParameterName = "@destinatie";
				paramDestinatie.Value = entity.IdDestinatie;
				comm.Parameters.Add(paramDestinatie);

				var paramData = comm.CreateParameter();
				paramData.ParameterName = "@dataPlecare";
				paramData.Value = entity.DataPlecare;
				comm.Parameters.Add(paramData);

				var paramLocuri = comm.CreateParameter();
				paramLocuri.ParameterName = "@nrLocuri";
				paramLocuri.Value = entity.NrLocuriDisponibile;
				comm.Parameters.Add(paramLocuri);

				var paramPlecare = comm.CreateParameter();
				paramPlecare.ParameterName = "@plecare";
				paramPlecare.Value = entity.LocPlecare;
				comm.Parameters.Add(paramPlecare);

				var result = comm.ExecuteNonQuery();
				if (result == 0)
					throw new RepositoryException("Cursa nu a fost adaugata!");
			}
			con.Close();
		}

		/// <summary>
		/// Update Cursa 
		/// Arunca RepositoryException in caz de esec
		/// </summary>
		/// <param name="entity">Cursa de updatat</param>
		public void Update(Cursa entity)
		{
			var con = SQLiteConnectionFactory.GetInstance().CreateConnection();
			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "update Curse " +
				                   "set Destinatie=@Destinatie, DataPlecare=@dataPlecare, NrLocuri=@nrLocuri, Plecare=@plecare " +
				                   "where Id=@id";


				var paramId = comm.CreateParameter();
				paramId.ParameterName = "@id";
				paramId.Value = entity.Id;
				comm.Parameters.Add(paramId);

				var paramDestinatie = comm.CreateParameter();
				paramDestinatie.ParameterName = "@destinatie";
				paramDestinatie.Value = entity.IdDestinatie;
				comm.Parameters.Add(paramDestinatie);

				var paramData = comm.CreateParameter();
				paramData.ParameterName = "@dataPlecare";
				paramData.Value = entity.DataPlecare;
				comm.Parameters.Add(paramData);

				var paramLocuri = comm.CreateParameter();
				paramLocuri.ParameterName = "@nrLocuri";
				paramLocuri.Value = entity.NrLocuriDisponibile;
				comm.Parameters.Add(paramLocuri);

				var paramPlecare = comm.CreateParameter();
				paramPlecare.ParameterName = "@plecare";
				paramPlecare.Value = entity.LocPlecare;
				comm.Parameters.Add(paramPlecare);

				var dataR = comm.ExecuteNonQuery();
				if (dataR == 0)
				{
					throw new RepositoryException("Cursa nu a fost modificata!");
				}
			}
			con.Close();
		}

		/// <summary>
		/// Find Cursa din Db
		/// </summary>
		/// <param name="id">Id-ul cursei de gasit</param>
		/// <returns>Cursa gasita sau null</returns>
		public Cursa Find(int id)
		{
			var con = SQLiteConnectionFactory.GetInstance().CreateConnection();

			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "select * from Curse where id=@id";

				var paramId = comm.CreateParameter();
				paramId.ParameterName = "@id";
				paramId.Value = id;
				comm.Parameters.Add(paramId);

				using (var dataR = comm.ExecuteReader())
				{
					while (dataR.Read())
					{
						var idCursa = dataR.GetInt32(0);
						var destinatie = dataR.GetInt32(1);
						var data = dataR.GetDateTime(2);
						var nrLocuri = dataR.GetInt32(3);
						var plecare = dataR.GetString(4);
						var c = new Cursa(idCursa, destinatie, data, nrLocuri, plecare);
						return c;
					}
				}

			}
			con.Close();
			return null;
		}

	}
}