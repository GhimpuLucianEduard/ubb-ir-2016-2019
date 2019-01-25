using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Data.SQLite;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CurseMPP.Utils
{
	class ConnectionFactory
	{
		protected ConnectionFactory()
		{
		}

		private static ConnectionFactory instance;

		public static ConnectionFactory getInstance()
		{
			if (instance == null)
			{
				instance = new ConnectionFactory();
			}
			return instance;
		}

		public IDbConnection createConnection()
		{
			string connectionString = ConfigurationManager.ConnectionStrings["CurseDB"].ConnectionString;
			Console.WriteLine("SQLite ---Se deschide o conexiune la  ... {0}", connectionString);
			var con =  new SQLiteConnection(connectionString);
			con.Open();
			return con;
		}
	}

}
