using System;

namespace Networking.DTOs
{	
	[Serializable]
	public class LocDTO
	{
		private int _nrLoc;
		private string _nume;
		private string _prenume;

		public LocDTO(int nrLoc, string nume, string prenume)
		{
			_nrLoc = nrLoc;
			_nume = nume;
			_prenume = prenume;
		}

		public int NrCurent
		{
			get { return _nrLoc; }
			set { _nrLoc = value; }
		}

		public string Nume
		{
			get { return _nume; }
			set { _nume = value; }
		}

		public string Prenume
		{
			get { return _prenume; }
			set { _prenume = value; }
		}
	}
}