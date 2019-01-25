namespace CurseMPP.Models
{
	public class Personal : HasId<int>
	{

		public Personal(int id, string user, string pass, string numes, string prenumes)
		{
			Id = id;
			userName = user;
			password = pass;
			nume = numes;
			prenume = prenumes;
		}


		public int Id { get; set; }
		public string userName { get; set; }
		public string password { get; set; }
		public string nume { get; set; }
		public string prenume { get; set; }

		public override string ToString()
		{
			return "id: " + Id + " userName: " + userName + " password: " + password + " nume: " + nume + " prenume: " + prenume;
		}
	}
}