using System.Data.Common;
using System.Data.Entity;
using System.Data.Entity.ModelConfiguration.Conventions;
using RestWepApiMppCurse.Models;

namespace RestWepApiMppCurse.Repo
{
	public class DbContextCurse : DbContext
	{
		public DbContextCurse() : base((DbConnection)SqLiteConnectionFactory.GetInstance().CreateConnection(), true)
		{
		}

		protected override void OnModelCreating(DbModelBuilder modelBuilder)
		{
			modelBuilder.Conventions.Remove<PluralizingTableNameConvention>();
			base.OnModelCreating(modelBuilder);
		}

		public DbSet<Cursa> Curse { get; set; }
	}
}