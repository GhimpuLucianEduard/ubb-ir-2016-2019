using System;
using Networking.DTOs;

namespace Networking.Requests
{	
	[Serializable]
	public class LoginRequest : IRequest
	{
		private PersonalDTO _personal;

		public LoginRequest(PersonalDTO personal)
		{
			_personal = personal;
		}

		public virtual PersonalDTO PersonalDto
		{
			get { return _personal; }
		}
		
	}
}