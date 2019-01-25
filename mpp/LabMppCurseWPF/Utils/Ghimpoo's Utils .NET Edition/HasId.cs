using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CurseMPP.Models
{	
	/// <summary>
	/// Interfata pentru clasele cu id
	/// </summary>
	/// <typeparam name="T">Tipul id-ului</typeparam>
	public interface HasId<T>
	{
		T Id { get; set; }
	}
}
