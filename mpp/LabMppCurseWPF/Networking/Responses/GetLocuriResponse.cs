using System;
using Networking.DTOs;

namespace Networking.Responses
{	
	[Serializable]
	public class GetLocuriResponse : IResponse
	{
		private LocDTO[] _locuri;

		public GetLocuriResponse(LocDTO[] locuri)
		{
			_locuri = locuri;
		}

		public LocDTO[] Locuri
		{
			get { return _locuri; }
		}
	}
}