using CurseMPP.Models;
using LabMppCurseWPF.Models;

namespace Networking.DTOs
{
	public static class DTOUtils
	{
		public static Personal GetFromDTO(PersonalDTO personalDto)
		{
			var username = personalDto.UserName;
			var passwd = personalDto.Passwd;
			return new Personal(username,passwd);
		}

		public static PersonalDTO GetFromDTO(Personal personal)
		{
			var username = personal.UserName;
			var passwd = personal.Password;
			return new PersonalDTO(username, passwd);
		}

		public static CursaDTO GetFromDTO(Cursa cursa)
		{
			var id = cursa.Id;
			var nrLocuri = cursa.NrLocuriDisponibile;
			var dataPlecare = cursa.DataPlecare;
			var locPlecare = cursa.LocPlecare;
			if (cursa.Destinatie != null)
			{
				var dest = cursa.Destinatie;
				return new CursaDTO(id, dataPlecare, locPlecare, nrLocuri, DTOUtils.GetFromDTO(dest));
			}
			else
			{
				var idDest = cursa.IdDestinatie;
				return new CursaDTO(id,dataPlecare,locPlecare,nrLocuri,idDest);
			}
		}

		public static Cursa GetFromDTO(CursaDTO cursa)
		{
			var id = cursa.Id;
			var nrLocuri = cursa.LocuriDisponibile;
			var dataPlecare = cursa.DataPlecare;
			var locPlecare = cursa.LocPlecare;
			if (cursa.Destinatie != null)
			{
				var dest = cursa.Destinatie;
				return new Cursa(id,DTOUtils.GetFromDTO(dest),dataPlecare,nrLocuri,locPlecare);
			}
			else
			{
				var idDest = cursa.IdDestinatie;
				return new Cursa(id,idDest,dataPlecare,nrLocuri,locPlecare);
			}
		}


		public static CursaDTO[] GetFromDTO(Cursa[] curse)
		{
			CursaDTO[] curseDTO = new CursaDTO[curse.Length];
			for (var i = 0; i < curse.Length; i++)
			{
				curseDTO[i] = GetFromDTO(curse[i]);
			}
			return curseDTO;
		}

		public static Cursa[] GetFromDTO(CursaDTO[] curseDTO)
		{
			Cursa[] curse = new Cursa[curseDTO.Length];
			for (var i = 0; i < curseDTO.Length; i++)
			{
				curse[i] = GetFromDTO(curseDTO[i]);
			}
			return curse;
		}


		public static Destinatie GetFromDTO(DestinatieDTO destinatieDto)
		{
			var id = destinatieDto.Id;
			var nume = destinatieDto.Nume;
			return new Destinatie(id,nume);
		}

		public static DestinatieDTO GetFromDTO(Destinatie destinatie)
		{
			var id = destinatie.Id;
			var nume = destinatie.Nume;
			return new DestinatieDTO(id, nume);
		}

		public static DestinatieDTO[] GetFromDTO(Destinatie[] destinatii)
		{
			DestinatieDTO[] destnatiiDTO = new DestinatieDTO[destinatii.Length];
			for (var i = 0; i < destinatii.Length; i++)
			{
				destnatiiDTO[i] = GetFromDTO(destinatii[i]);
			}
			return destnatiiDTO;
		}

		public static Destinatie[] GetFromDTO(DestinatieDTO[] destinatiiDTO)
		{
			Destinatie[] destnatii = new Destinatie[destinatiiDTO.Length];
			for (var i = 0; i < destinatiiDTO.Length; i++)
			{
				destnatii[i] = GetFromDTO(destinatiiDTO[i]);
			}
			return destnatii;
		}

		public static Loc GetFromDTO(LocDTO loc)
		{
			var nr = loc.NrCurent;
			var nume = loc.Nume;
			var prenume = loc.Prenume;
			return new Loc(nr,nume,prenume);
		}

		public static LocDTO GetFromDTO(Loc loc)
		{
			var nr = loc.NrCurent;
			var nume = loc.Nume;
			var prenume = loc.Prenume;
			return new LocDTO(nr, nume, prenume);
		}

		public static LocDTO[] GetFromDTO(Loc[] locuri)
		{
			LocDTO[] locuriDTO = new LocDTO[locuri.Length];
			for (var i = 0; i < locuri.Length; i++)
			{
				locuriDTO[i] = GetFromDTO(locuri[i]);
			}
			return locuriDTO;
		}

		public static Loc[] GetFromDTO(LocDTO[] locuriDTO)
		{
			Loc[] locuri = new Loc[locuriDTO.Length];
			for (var i = 0; i < locuriDTO.Length; i++)
			{
				locuri[i] = GetFromDTO(locuriDTO[i]);
			}
			return locuri;
		}

	}
}