using System;
using LabMppCurseWPF.Models;
using Networking.DTOs;

namespace Networking.Requests
{	
	[Serializable] 
	public class FindCursaRequest : IRequest
	{
		private DateTime _data;
		private DestinatieDTO _destinatie;

		public FindCursaRequest(DateTime data, DestinatieDTO destinatie)
		{
			_data = data;
			_destinatie = destinatie;
		}

		public virtual DateTime Data
		{
			get { return _data; }
			set { _data = value; }
		}

		public virtual DestinatieDTO Destinatie
		{
			get { return _destinatie; }
			set { _destinatie = value; }
		}
	}
}