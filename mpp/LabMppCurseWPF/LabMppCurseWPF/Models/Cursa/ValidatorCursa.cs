using LabMppCurseWPF.Models;

namespace CurseMPP.Models
{	
	/// <summary>
	/// Validator Cursa
	/// </summary>
	public class ValidatorCursa : IValidator<Cursa>
	{	
		/// <summary>
		/// Functie de validare a unei curse, arunca ValidationException daca cursa nu e valida
		/// </summary>
		/// <param name="entity"></param>
		public void Validate(Cursa entity)
		{
			var errorMessage = "";
			if (entity.NrLocuriDisponibile < 0 || entity.NrLocuriDisponibile > 18)
			{
				errorMessage += "Numarul locurilor nu poate fi mai mic ca 0 sau mai mare ca 18! ";
			}

			if (entity.IdDestinatie <= 0)
			{
				errorMessage += "Destinatia invalida! ";
			}

			if (errorMessage.CompareTo("") != 0)
			{
				throw new ValidationException(errorMessage);
			}
		}
	}
}