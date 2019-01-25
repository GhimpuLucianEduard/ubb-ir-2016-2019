using System;
using Networking.DTOs;

namespace Networking.Requests
{	
	[Serializable]
	public class AddRezervareRequest : IRequest
	{
		private AdaugaRezervareDTO _dateAdaugaRezervareDto;

		public AddRezervareRequest(AdaugaRezervareDTO dateAdaugaRezervareDto)
		{
			_dateAdaugaRezervareDto = dateAdaugaRezervareDto;
		}

		public AdaugaRezervareDTO AdaugaRezervareDto
		{
			get { return _dateAdaugaRezervareDto; }
		}

	}
}