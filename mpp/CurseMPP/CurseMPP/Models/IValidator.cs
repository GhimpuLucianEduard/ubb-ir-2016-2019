using System;

namespace CurseMPP.Models
{
	public interface IValidator<T>
	{
		void validate(T elem);
	}

}