namespace CurseMPP.Models
{
	public class ValidatorCursa : IValidator<Cursa>
	{
		public void validate(Cursa elem)
		{
			string msg = "";
			if (elem.nrLocuriDisponibile < 0 || elem.nrLocuriDisponibile > 18)
			{
				msg += "numar locuri invalid";
			}

			if (elem.idDestinatie <= 0)
			{
				msg += "destinatie invalida";
			}

			if (msg.CompareTo("") != 0)
			{
				throw new ValidationException(msg);
			}
		}
	}
}