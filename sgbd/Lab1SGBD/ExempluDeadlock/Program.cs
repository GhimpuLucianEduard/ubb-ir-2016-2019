using System;
using System.Data;
using System.Data.SqlClient;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ExempluDeadlock
{
	class Program
	{	
		static void Main(string[] args)
		{
			var ex = new ExempluDL();
			Task.Run(() => ex.ExecuteStoredProcedure("ExDeadlock1"));
			Task.Run(() => ex.ExecuteStoredProcedure("ExDeadlock2Low"));
			Console.ReadLine();
		}
	}
}
