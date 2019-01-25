namespace CurseMPP.Models
{
	public class ValidatorPersonal : IValidator<Personal>
	{
		public void validate(Personal elem)
		{
			string msg = "";

			if (elem.nume.CompareTo("") == 0)
			{
				msg += "nume vid";
			}

			if (elem.prenume.CompareTo("") == 0)
			{
				msg+="prenumele vid";
			}

			if (elem.userName.CompareTo("") == 0)
			{
				msg += "username vid";
			}

			if (elem.password.CompareTo("") == 0)
			{
				msg += "parola vida";
			}

			if (msg.CompareTo("") != 0)
			{
				throw new ValidationException(msg);
			}

		}
	}
}