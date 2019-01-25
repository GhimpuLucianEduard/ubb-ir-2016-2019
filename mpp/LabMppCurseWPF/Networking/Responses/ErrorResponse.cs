using System;

namespace Networking.Responses
{
	[Serializable]
	public class ErrorResponse : IResponse
	{
		private string _errorMessage;

		public ErrorResponse(string errorMessage)
		{
			_errorMessage = errorMessage;
		}

		public virtual string ErrorMessage
		{
			get { return _errorMessage; }
		}
	}
}