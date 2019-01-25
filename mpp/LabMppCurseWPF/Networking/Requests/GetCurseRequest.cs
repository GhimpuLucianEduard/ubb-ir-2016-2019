using System;
using Networking.DTOs;

namespace Networking.Requests
{
	[Serializable]
	public class GetCurseRequest : IRequest
	{
		private PersonalDTO _personal;

		public GetCurseRequest(PersonalDTO personal)
		{
			_personal = personal;
		}

		public virtual PersonalDTO PersonalDto
		{
			get { return _personal; }
		}
	}
}