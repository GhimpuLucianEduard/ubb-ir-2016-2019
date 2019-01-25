using System.Data.Common;
using System.Data.Entity;
using System.Data.Entity.ModelConfiguration.Conventions;
using LabMppCurseWPF.Models;
using LabMppCurseWPF.NET_Edition;

namespace CurseMPP.Models
{
	public class DbContextCurse : DbContext
	{
		public DbContextCurse() : base((DbConnection) SQLiteConnectionFactory.GetInstance().CreateConnection(), true)
		{
		}

		protected override void OnModelCreating(DbModelBuilder modelBuilder)
		{
			modelBuilder.Conventions.Remove<PluralizingTableNameConvention>();
			base.OnModelCreating(modelBuilder);
		}

		public DbSet<Destinatie> Destinatii { get; set; }
	}
}