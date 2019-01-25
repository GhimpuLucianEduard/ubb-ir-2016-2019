using System;

namespace LabMppCurseWPF.Service
{
	public class ServiceException : ApplicationException
	{
		/// <summary>
		/// Exceptie specifica servicelor
		/// </summary>
		public ServiceException(string message) : base(message)
		{
		}
	
	}
}