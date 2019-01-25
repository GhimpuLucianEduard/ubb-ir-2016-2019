using CurseMPP.Models;

namespace LabMppCurseWPF.Models
{	
	/// <summary>
	/// Validator pentru Destinatie
	/// </summary>
	public class ValidatorDestinatie : IValidator<Destinatie>
	{	
		/// <summary>
		/// Functie care valideaza o destinatie, arunca ValidationException daca entitatea nu e valida
		/// </summary>
		/// <param name="entity">Destinatia de validat</param>
		public void Validate(Destinatie entity)
		{	
			var errorMessage = "";

			if (entity.Nume.CompareTo("") == 0)
			{
				errorMessage += "Numele unei destinatii nu poate fi vid! ";
			}

			if (errorMessage.CompareTo("") != 0)
			{
				throw new ValidationException(errorMessage);
			}
		}
	}
}