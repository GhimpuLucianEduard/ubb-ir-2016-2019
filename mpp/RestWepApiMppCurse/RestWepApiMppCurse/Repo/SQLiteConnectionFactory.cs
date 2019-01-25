using System;
using System.Configuration;
using System.Data;
using System.Data.SQLite;

namespace RestWepApiMppCurse.Repo
{
	/// <summary>
	/// Clasa singleton folosita la crearea conexiunilor
	/// cu o baza de date de tip SQLite
	/// </summary>
	public class SqLiteConnectionFactory
	{

		#region Singleton
		/// <summary>
		/// instanta unica
		/// </summary>
		private static SqLiteConnectionFactory _instance;

		/// <summary>
		/// Constructorul protejat
		/// </summary>
		protected SqLiteConnectionFactory() { }

		/// <summary>
		/// Functie care returneaza instanta curenta daca exista
		/// Daca nu exista se creeaza o noua instanta
		/// </summary>
		/// <returns>Instanta de SQLiteConnectionFactory</returns>
		public static SqLiteConnectionFactory GetInstance()
		{
			return _instance ?? (_instance = new SqLiteConnectionFactory());
		}

		#endregion

		/// <summary>
		/// Functie folosita la crearea unei noi conexiuni
		/// </summary>
		/// <returns>Returneaza o noua conexiune deschisa la baza de date specificata
		/// in connection string</returns>
		public IDbConnection CreateConnection()
		{
			var connectionString = ConfigurationManager.ConnectionStrings["CurseDB"].ConnectionString;
			//Console.WriteLine("Se deschide o conexiune la {0}", connectionString);
			var connection = new SQLiteConnection(connectionString);
			connection.Open();
			return connection;
		}
	}
}