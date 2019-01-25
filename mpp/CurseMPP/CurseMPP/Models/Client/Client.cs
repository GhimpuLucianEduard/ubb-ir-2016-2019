namespace CurseMPP.Models
{
	public class Client : HasId<int>
	{
		public Client(int id, string nume, string prenume)
		{
			Id = id;
			this.nume = nume;
			this.prenume = prenume;
		}

		public int Id { get; set; }
		public string nume { get; set; }
		public string prenume { get; set; }

		public override string ToString()
		{
			return "Id: " + Id + " nume: " + nume + " prenume: " + prenume;
		}
	}
}