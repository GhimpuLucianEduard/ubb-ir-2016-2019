using System;
using LabMppCurseWPF.Models;
using Networking.DTOs;

namespace Networking.Responses
{	
	[Serializable]
	public class GetCurseResponse : IResponse
	{
		private CursaDTO[] _curse;

		public GetCurseResponse(CursaDTO[] curse)
		{
			_curse = curse;
		}

		public virtual CursaDTO[] Curse
		{
			get { return _curse;  }
		}
	}
}