using System;

namespace Networking.DTOs
{	
	[Serializable]
	public class AdaugaRezervareDTO
	{
		private int _idCursa;
		private int _nrLocuri;
		private string _nume;
		private string _prenume;

		public AdaugaRezervareDTO(int idCursa, int nrLocuri, string nume, string prenume)
		{
			_idCursa = idCursa;
			_nrLocuri = nrLocuri;
			_nume = nume;
			_prenume = prenume;
		}

		public virtual int IdCursa
		{
			get { return _idCursa; }
			set { _idCursa = value; }
		}

		public virtual int NrLocuri
		{
			get { return _nrLocuri; }
			set { _nrLocuri = value; }
		}

		public virtual string Nume
		{
			get { return _nume; }
			set { _nume = value; }
		}

		public virtual string Prenume
		{
			get { return _prenume; }
			set { _prenume = value; }
		}
	}
}