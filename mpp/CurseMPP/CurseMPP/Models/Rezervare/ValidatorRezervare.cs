namespace CurseMPP.Models
{
	public class ValidatorRezervare : IValidator<Rezervare>
	{
		public void validate(Rezervare elem)
		{
			string msg = "";
			if (elem.nrLocuri <= 0 || elem.nrLocuri >= 18)
			{
				msg += "numar locuri invalid";
			}

			if (msg.CompareTo("") != 0)
			{
				throw new ValidationException(msg);
			}
		}
	}
}