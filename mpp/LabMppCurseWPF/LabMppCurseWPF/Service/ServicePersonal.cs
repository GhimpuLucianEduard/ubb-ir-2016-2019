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

		public static Personal CautaPersonalDupaDetalii(string user, string parola)
		{
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