using System;
using System.Data;
using System.Data.SqlClient;

namespace ExempluDeadlock
{
	public class ExempluDL
	{
		public void ExecuteStoredProcedure(string text)
		{
			var retryCount = 0;
			while (retryCount < 10)
			{
				Console.WriteLine("Inceput executia pentru procedura " + text);
				try
				{
					var conn = new SqlConnection("Data Source=GETH-MAINFRAME;Initial Catalog=SGBD;Integrated Security=True");
					conn.Open();
					var command = new SqlCommand(text, conn);
					command.CommandType = CommandType.StoredProcedure;
					command.ExecuteNonQuery();
					retryCount = 11;
				}
				catch (SqlException ex)
				{
					if (ex.Number == 1205)
					{
						Console.WriteLine("Deadlock in " + text);
						Console.WriteLine("Retry pentru " + text);
						retryCount++;
					}
				}
				finally
				{
					Console.WriteLine("terminat executia la" + text);
				}
			}
		}
	}
}