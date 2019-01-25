using System.Linq;
using LabMppCurseWPF.Models;
using LabMppCurseWPF.NET_Edition;
using LabMppCurseWPF.Repository;

namespace LabMppCurseWPF.Service
{
	/// <summary>
	/// Clasa statica care se ocupa cu operatii de baza pentru Personal
	/// </summary>
	internal static class ServicePersonal
	{
		/// <summary>
		/// Repository cu operatii crud pe perssonal
		/// </summary>
		private static IRepository<int, Personal> _repository = new RepositoryPersonal();
		//private static IRepository<int, Personal> _repository = new MockPersonalRepo();


		public static Personal CautaPersonalDupaDetalii(string user, string parola)
		{

			Personal rez = null;

			_repository.FindAll().ToList().ForEach(p =>
			{
				if ((p.UserName.CompareTo(user) == 0) && (p.Password.CompareTo(parola) == 0))
				{
					rez = p;
				}
			});

			return rez;

			var con = SQLiteConnectionFactory.GetInstance().CreateConnection();

			using (var command = con.CreateCommand())
			{
				command.CommandText = "SELECT * " +
				                      "FROM Personal P " +
				                      "WHERE P.Username = @user and P.Password=@pass";

				var paramUser = command.CreateParameter();
				paramUser.ParameterName = "@user";
				paramUser.Value = user;
				command.Parameters.Add(paramUser);

				var paramPass = command.CreateParameter();
				paramPass.ParameterName = "@pass";
				paramPass.Value = parola;
				command.Parameters.Add(paramPass);

				using (var dataReader = command.ExecuteReader())
				{
					if (dataReader.Read())
					{
						var idPersonal = dataReader.GetInt32(0);
						var userName = dataReader.GetString(1);
						var password = dataReader.GetString(2);
						var nume = dataReader.GetString(3);
						var prenume = dataReader.GetString(4);
						var personal = new Personal(idPersonal, userName, password, nume, prenume);
						return personal;
					}
				}

			}
			con.Close();
			return null;
		}

		}
}