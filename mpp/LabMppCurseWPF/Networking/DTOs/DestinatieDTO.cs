using System;

namespace Networking.DTOs
{	
	[Serializable]
	public class DestinatieDTO
	{
		private int _id;
		private string _nume;

		public DestinatieDTO(int id, string nume)
		{
			_id = id;
			_nume = nume;
		}

		public int Id
		{
			get { return _id; }
			set { _id = value; }
		}

		public string Nume
		{
			get { return _nume; }
			set { _nume = value; }
		}
	}
}