using System;

namespace CurseMPP.Repository
{
	public class RepositoryException : ApplicationException
	{
		public RepositoryException(string message) : base(message)
		{
		}
	}
}