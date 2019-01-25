using CurseMPP.Models;

namespace LabMppCurseWPF.Models
{
	public class Rezervare : HasId<int>
	{

		#region Propietati Rezervare

		public int Id { get; set; }
		public int IdClient { get; set; }
		public Client Client { get; set; }
		public int IdCursa { get; set; }
		public Cursa Cursa { get; set; }
		public int NrLocuri { get; set; }

		#endregion

		#region Constructori

		/// <summary>
		/// Constructor rezervare cand cheile straine sunt date ca si id-uri
		/// </summary>
		/// <param name="id">Id rezervare</param>
		/// <param name="idClient">Id client</param>
		/// <param name="idCursa">Id Cursa</param>
		/// <param name="nrLocuri">Nr de locuri rezervate</param>
		public Rezervare(int id, int idClient, int idCursa, int nrLocuri)
		{
			Id = id;
			this.IdClient = idClient;
			this.IdCursa = idCursa;
			this.NrLocuri = nrLocuri;
		}

		/// <summary>
		/// Constructor Rezervare cand cheile straine sunt date ca si referinta la clasa
		/// </summary>
		/// <param name="id">Id Rezervare</param>
		/// <param name="client">Clientul care rezerva</param>
		/// <param name="cursa">Cursa la care e asociata rezervarea</param>
		/// <param name="nrLocuri">Numarul de locuri rezervate</param>
		public Rezervare(int id, Client client, Cursa cursa, int nrLocuri)
		{
			Id = id;
			IdClient = Client.Id;
			Client = client;
			IdCursa = Cursa.Id;
			Cursa = cursa;
			NrLocuri = nrLocuri;
		}

		#endregion

		//TODO toString
	}
}
