using System;

namespace CurseMPP.Models
{
	public class ValidationException : ApplicationException
	{
		public ValidationException(string message) : base(message) { }
	}
}