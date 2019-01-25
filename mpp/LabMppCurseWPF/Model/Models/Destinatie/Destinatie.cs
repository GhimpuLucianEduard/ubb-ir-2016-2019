using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using CurseMPP.Models;

namespace LabMppCurseWPF.Models
{
	/// <summary>
	/// Clasa Destinatie
	/// </summary>
	[Serializable]
	[Table("Destinatii")]
	public class Destinatie : HasId<int>
	{
		#region Propietati Destinatie

		[Column("Id")]
		[Key]
		public int Id { get; set; }

		[Column("Nume")]
		public string Nume { get; set; }

		#endregion

		/// <summary>
		/// Constructor destinatie
		/// </summary>
		/// <param name="id">Id-ul destinatiei</param>
		/// <param name="nume">Numele destinatiei</param>
		public Destinatie(int id, string nume)
		{
			Id = id;
			this.Nume = nume;
		}

		public Destinatie() { }

		#region Overrided Functions

		public override string ToString()
		{
			return Nume;
		}

		#endregion

	}
}
