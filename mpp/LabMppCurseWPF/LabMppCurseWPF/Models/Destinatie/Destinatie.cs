using CurseMPP.Models;

namespace LabMppCurseWPF.Models
{	
	/// <summary>
	/// Clasa Destinatie
	/// </summary>
	public class Destinatie : HasId<int>
	{
		#region Propietati Destinatie

		public int Id { get; set; }
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

		#region Overrided Functions

		public override string ToString()
		{
			return Nume;
		}

		#endregion

	}
}
