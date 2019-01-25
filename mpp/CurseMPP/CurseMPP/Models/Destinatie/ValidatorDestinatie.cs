namespace CurseMPP.Models
{
	public class ValidatorDestinatie : IValidator<Destinatie>
	{
		public void validate(Destinatie elem)
		{
			string msg = "";
			if (elem.nume.CompareTo("") == 0)
			{
				msg += "numele e vid";
			}

			if (msg.CompareTo("") != 0)
			{
				throw new ValidationException(msg);
			}
		}
	}
}