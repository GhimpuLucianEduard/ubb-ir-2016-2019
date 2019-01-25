using System;
using Networking.DTOs;

namespace Networking.Responses
{	
	[Serializable]
	public class GetDestinatiiResponse : IResponse
	{
		private DestinatieDTO[] _destinatii;

		public GetDestinatiiResponse(DestinatieDTO[] destinatii)
		{
			_destinatii = destinatii;
		}

		public virtual DestinatieDTO[] Destinatii
		{
			get { return _destinatii; }
		}
	}
}