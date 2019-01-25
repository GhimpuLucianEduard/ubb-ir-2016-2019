using CurseMPP.Models;

namespace LabMppCurseWPF.Models
{	
	/// <summary>
	/// Validator de Personal
	/// </summary>
	public class ValidatorPersonal : IValidator<Personal>
	{
		/// <summary>
		/// Functia de validare, arunca ValidationException daca Personalul nu e valid
		/// </summary>
		/// <param name="entity"></param>
		public void Validate(Personal entity)
		{
			var errorMessage = "";

			if (entity.Nume.CompareTo("") == 0)
			{
				errorMessage += "Numele unui personal nu poate fi vid! ";
			}

			if (entity.Prenume.CompareTo("") == 0)
			{
				errorMessage += "Prenumele unui personal nu poate fi vid! ";
			}

			if (entity.UserName.CompareTo("") == 0)
			{
				errorMessage += "Username-ul unui personal nu poate fi vid! ";
			}

			if (entity.Password.CompareTo("") == 0)
			{
				errorMessage += "Parola unui personal nu poate fi vida! ";
			}

			if (errorMessage.CompareTo("") != 0)
			{
				throw new ValidationException(errorMessage);
			}

		}
	}
}