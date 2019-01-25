using System;

namespace CurseMPP.Models
{	
	/// <summary>
	/// Exceptie specifica validatoarelor
	/// </summary>
	public class ValidationException : ApplicationException
	{	
		public ValidationException(string message) : base(message) { }
	}
}