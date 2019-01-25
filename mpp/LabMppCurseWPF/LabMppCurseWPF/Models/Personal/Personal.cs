using CurseMPP.Models;

namespace LabMppCurseWPF.Models
{	
	/// <summary>
	/// Clasa Personal
	/// </summary>
	public class Personal : HasId<int>
	{
		#region Propietati Personal

		public int Id { get; set; }
		public string UserName { get; set; }
		public string Password { get; set; }
		public string Nume { get; set; }
		public string Prenume { get; set; }

		#endregion

		/// <summary>
		/// Constructor personal
		/// </summary>
		/// <param name="id">Id-ul personalului</param>
		/// <param name="user">Username asociat</param>
		/// <param name="pass">Parola asociata</param>
		/// <param name="nume">Nume personal</param>
		/// <param name="prenume">Prenume personal</param>
		public Personal(int id, string user, string pass, string nume, string prenume)
		{
			Id = id;
			UserName = user;
			Password = pass;
			Nume = nume;
			Prenume = prenume;
		}

		#region Overrided Functions

		public override string ToString()
		{
			return "id: " + Id + " userName: " + UserName + " password: " + Password + " nume: " + Nume + " prenume: " + Prenume;
		}

		#endregion


	}
}