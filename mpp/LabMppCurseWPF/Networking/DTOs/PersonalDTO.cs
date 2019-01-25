using System;

namespace Networking.DTOs
{	
	[Serializable]
	public class PersonalDTO
	{
		private string _userName;
		private string _passwd;

		public PersonalDTO(string userName, string passwd)
		{
			_userName = userName;
			_passwd = passwd;
		}

		public virtual string UserName
		{
			get { return _userName; }
			set { _userName = value; }
		}

		public virtual string Passwd
		{
			get { return _passwd; }
			set { _passwd = value;  }
		}

	}
}