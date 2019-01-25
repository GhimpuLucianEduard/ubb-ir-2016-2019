using CurseMPP.Models;

namespace LabMppCurseWPF.Models
{	
	/// <summary>
	/// Validator pentru Client
	/// </summary>
	public class ValidatorClient : IValidator<Client>
	{
		/// <summary>
		/// Valideaza un client, arunca ValidationException daca una din conditii nu e respectata
		/// </summary>
		/// <param name="entity">Clientul de validat</param>
		public void Validate(Client entity)
		{
			var errorMessage = "";

			if (entity.Nume.CompareTo("")==0)
			{
				errorMessage += "Numele unui client nu poate fi vid! ";
			}

			if (entity.Prenume.CompareTo("")==0)
			{
				errorMessage += "Prenumele unui client nu poate fi vid! ";
			}

			if (errorMessage.CompareTo("") != 0)
			{
				throw new ValidationException(errorMessage);
			}

		}
	}
}