using System;
using LabMppCurseWPF.Models;

namespace Networking.DTOs
{	
	[Serializable]
	public class CursaDTO
	{
		private int _id;
		private DateTime _dataPlecare;
		private string _locPlecare;
		private int _locuriDisponbile;
		private DestinatieDTO _destinatie;
		private int _idDestinatie;

		public CursaDTO(int id, DateTime dataPlecare, string locPlecare, int locuriDisponbile, DestinatieDTO destinatie)
		{
			_id = id;
			_dataPlecare = dataPlecare;
			_locPlecare = locPlecare;
			_locuriDisponbile = locuriDisponbile;
			_destinatie = destinatie;
			//_idDestinatie = destinatie.Id;
		}

		public CursaDTO(int id, DateTime dataPlecare, string locPlecare, int locuriDisponbile, int destinatie)
		{
			_id = id;
			_dataPlecare = dataPlecare;
			_locPlecare = locPlecare;
			_locuriDisponbile = locuriDisponbile;
			_idDestinatie = destinatie;
		}

		public virtual int Id
		{
			get { return _id; }
			set { _id = value; }
		}

		public virtual DateTime DataPlecare
		{
			get { return _dataPlecare; }
			set { _dataPlecare = value; }
		}

		public virtual string LocPlecare
		{
			get { return _locPlecare; }
			set { _locPlecare = value; }
		}

		public virtual int LocuriDisponibile
		{
			get { return _locuriDisponbile; }
			set { _locuriDisponbile = value; }
		}

		public virtual int IdDestinatie
		{
			get { return _idDestinatie; }
			set { _idDestinatie = value; }
		}

		public virtual DestinatieDTO Destinatie
		{
			get { return _destinatie; }
			set { _destinatie = value; }
		}

	}
}