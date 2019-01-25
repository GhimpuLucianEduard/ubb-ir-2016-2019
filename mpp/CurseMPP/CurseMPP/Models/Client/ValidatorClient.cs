namespace CurseMPP.Models
{
	public class ValidatorClient : IValidator<Client>
	{
		public void validate(Client elem)
		{
			string msg = "";

			if (elem.nume.CompareTo("")==0)
			{
				msg += "Nume vid";
			}

			if (elem.prenume.CompareTo("")==0)
			{
				msg += "Prenume vid";
			}

			if (msg.CompareTo("") != 0)
			{
				throw new ValidationException(msg);
			}


		}
	}
}