using System;
using Networking.DTOs;

namespace Networking.Responses
{	
	[Serializable]
	public class FindCursaResponse : IResponse
	{
		private CursaDTO _cursaDto;

		public FindCursaResponse(CursaDTO cursaDto)
		{
			_cursaDto = cursaDto;
		}

		public virtual CursaDTO Cursa
		{
			get { return _cursaDto; }
			set { _cursaDto = value; }
		}
	}
}