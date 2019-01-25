using System.Collections.Generic;
using System.Linq;
using DomainLib.Model;

namespace DomainLib.Repository
{
	public class StaffRepository
	{
		public Staff Find(int id)
		{
			using (var context = new CercetasiEntities())
			{
				return context.Staffs.Find(id);
			}
		}

		public IEnumerable<Staff> GetAll()
		{
			using (var context = new CercetasiEntities())
			{
				return context.Staffs.ToList();
			}
		}
	}
}