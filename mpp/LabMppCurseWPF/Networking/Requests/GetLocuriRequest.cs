using System;

namespace Networking.Requests
{	
	[Serializable]
	public class GetLocuriRequest : IRequest
	{
		private int _idCursa;

		public GetLocuriRequest(int id)
		{
			_idCursa = id;
		}

		public virtual int IdCursa
		{
			get { return _idCursa; }
		}

	}
}