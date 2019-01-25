using System;
using System.Collections.Generic;
using System.Data;
using LabMppCurseWPF.Models;
using LabMppCurseWPF.NET_Edition;

namespace LabMppCurseWPF.Repository
{	
	/// <summary>
	/// Repo DB personal
	/// </summary>
	public class RepositoryPersonal : IRepository<int,Personal>
	{
		private ValidatorPersonal ValidatorPersonal;

		public RepositoryPersonal()
		{
			ValidatorPersonal = new ValidatorPersonal();
		}

		/// <summary>
		/// Find Personal, returneaza Personal cu id-ul specificat daca exista in Db
		/// Null altfel
		/// </summary>
		/// <param name="id">Id-ul personalului de cautat</param>
		/// <returns></returns>
		public Personal Find(int id)
		{
			var con = SQLiteConnectionFactory.GetInstance().CreateConnection();

			using (var command = con.CreateCommand())
			{
				command.CommandText = "SELECT * " +
									  "FROM Personal P " +
				                      "WHERE P.Id = @id";
				var paramId = command.CreateParameter();
				paramId.ParameterName = "@id";
				paramId.Value = id;
				command.Parameters.Add(paramId);

				using (var dataReader = command.ExecuteReader())
				{
					if (dataReader.Read())
					{
						var idPersonal = dataReader.GetInt32(0);
						var userName = dataReader.GetString(1);
						var password = dataReader.GetString(2);
						var nume = dataReader.GetString(3);
						var prenume = dataReader.GetString(4);
						var personal = new Personal(idPersonal,userName,password,nume,prenume);
						return personal;
					}
				}

			}
			con.Close();
			return null;
		}
		
		/// <summary>
		/// Find all Personal din DB
		/// </summary>
		/// <returns>O lista de Personal</returns>
		public IEnumerable<Personal> FindAll()
		{
			var con = SQLiteConnectionFactory.GetInstance().CreateConnection();

			var listaPersonal = new List<Personal>();

			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "select * from Personal";

				using (var dataR = comm.ExecuteReader())
				{
					while (dataR.Read())
					{
						var id = dataR.GetInt32(0);
						var user = dataR.GetString(1);
						var pass = dataR.GetString(2);
						var nume = dataR.GetString(3);
						var prenume = dataR.GetString(4);
						var p = new Personal(id, user, pass, nume, prenume);
						listaPersonal.Add(p);
					}
				}
			}
			con.Close();
			return listaPersonal;
		}

		/// <summary>
		/// Add Personal, adauga personalul in DB
		/// Arunca RepositoryException daca nu se poate
		/// </summary>
		/// <param name="entity"></param>
		public void Add(Personal entity)
		{
			ValidatorPersonal.Validate(entity);

			var con = SQLiteConnectionFactory.GetInstance().CreateConnection();

			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "INSERT into Personal " +
				                   "values (@id, @userName, @password, @nume, @prenume)";

				var paramId = comm.CreateParameter();
				paramId.ParameterName = "@id";
				paramId.Value = entity.Id;
				comm.Parameters.Add(paramId);

				var paramUser = comm.CreateParameter();
				paramUser.ParameterName = "@userName";
				paramUser.Value = entity.UserName;
				comm.Parameters.Add(paramUser);

				var paramPass = comm.CreateParameter();
				paramPass.ParameterName = "@password";
				paramPass.Value = entity.Password;
				comm.Parameters.Add(paramPass);

				var paramNume = comm.CreateParameter();
				paramNume.ParameterName = "@nume";
				paramNume.Value = entity.Nume;
				comm.Parameters.Add(paramNume);

				var paramPrenume = comm.CreateParameter();
				paramPrenume.ParameterName = "@prenume";
				paramPrenume.Value = entity.Prenume;
				comm.Parameters.Add(paramPrenume);
				
				
				var result = comm.ExecuteNonQuery();
				if (result == 0)
					throw new RepositoryException("Personalul nu putut fi adaugat!!");

			}
			con.Close();
		}

		/// <summary>
		/// Stergee Personal din DB
		/// Arunca Repository exception in caz de esec
		/// </summary>
		/// <param name="id">Id-ul personalului de sters</param>
		public void Delete(int id)
		{
			var con = SQLiteConnectionFactory.GetInstance().CreateConnection();

			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "delete from Personal where id=@id";
				var paramId = comm.CreateParameter();
				paramId.ParameterName = "@id";
				paramId.Value = id;
				comm.Parameters.Add(paramId);
				var dataR = comm.ExecuteNonQuery();
				if (dataR == 0)
				{
					throw new RepositoryException("Personalul nu a fost stears!");
				}

			}
			con.Close();
		}

		/// <summary>
		/// Update Personal
		/// Arunca RepositoryException in caz de esec
		/// </summary>
		/// <param name="entity">Entitatea noua, cu id-ul entitatii de updatat</param>
		public void Update(Personal entity)
		{
			var con = SQLiteConnectionFactory.GetInstance().CreateConnection();
			ValidatorPersonal.Validate(entity);
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
				paramUser.Value = entity.UserName;
				comm.Parameters.Add(paramUser);

				var paramPass = comm.CreateParameter();
				paramPass.ParameterName = "@password";
				paramPass.Value = entity.Password;
				comm.Parameters.Add(paramPass);

				var paramNume = comm.CreateParameter();
				paramNume.ParameterName = "@nume";
				paramNume.Value = entity.Nume;
				comm.Parameters.Add(paramNume);

				var paramPrenume = comm.CreateParameter();
				paramPrenume.ParameterName = "@prenume";
				paramPrenume.Value = entity.Prenume;
				comm.Parameters.Add(paramPrenume);

				var dataR = comm.ExecuteNonQuery();
				if (dataR == 0)
				{
					throw new RepositoryException("Personal nu a fost modificat!");
				}
			}
			con.Close();
		}
	}
}