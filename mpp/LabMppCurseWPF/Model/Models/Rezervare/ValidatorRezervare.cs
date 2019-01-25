using LabMppCurseWPF.Models;

namespace CurseMPP.Models
{	
	/// <summary>
	/// Validator Rezervare
	/// </summary>
	public class ValidatorRezervare : IValidator<Rezervare>
	{	
		/// <summary>
		/// Functia de validare pentru o Rezervare, arunca ValidationExeption in cazul 
		/// in care rezervarea nu e valida
		/// </summary>
		/// <param name="entity">Rezervarea de validat</param>
		public void Validate(Rezervare entity)
		{
			var errorMessage = "";

			if (entity.NrLocuri <= 0 || entity.NrLocuri >= 18)
			{
				errorMessage += "Numarul de locuri nu poate fi mai mic de cat 0 sau mai mare de cat 18!";
			}

			if (errorMessage.CompareTo("") != 0)
			{
				throw new ValidationException(errorMessage);
			}
		}
	}
}