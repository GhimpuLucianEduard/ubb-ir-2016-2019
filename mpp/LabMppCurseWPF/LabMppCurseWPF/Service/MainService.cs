using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using CurseMPP.Models;
using LabMppCurseWPF.Models;
using LabMppCurseWPF.NET_Edition;


namespace LabMppCurseWPF.Service
{
	public static class MainService
	{

		/// <summary>
		/// Functie care se ocupa de login
		/// </summary>
		/// <param name="nume">username</param>
		/// <param name="parola">password</param>
		/// <returns>true daca credentialele sunt corecte, false altfel</returns>
		public static bool handleLogin(string nume, string parola)
		{
			return ServicePersonal.CautaPersonalDupaDetalii(nume, parola) != null;
		}


		/// <summary>
		/// Functie care returneaza toate cursele pentru view
		/// Pune destinatia potrivita ca sa poata fie afisata de view
		/// </summary>
		/// <returns>Lista cu cursele de afisat pentru view</returns>
		public static IEnumerable<Cursa> FindAllCurseForView()
		{
			var listaCurse = ServiceCursa.FindAllCurse();
			var listaDestinatii = ServiceDestinatie.FindAllDestinatii();
			listaCurse.ToList().ForEach(x =>
			{
				listaDestinatii.ToList().ForEach(y =>
				{
					if (x.IdDestinatie == y.Id)
					{
						x.Destinatie = y;
					}
				});
			});

			
			return listaCurse;
		}
		
		/// <summary>
		/// Genereaza o lista de locuri pentru o anumita cursa
		/// </summary>
		/// <param name="id">Id-ul cursei</param>
		/// <returns>O lista de locuri</returns>
		public static IEnumerable<Loc> GenereazaLocuriPentruCursa(int id)
		{	
			//genereaza lista cu toate 18 locuri libere
			var listaLocuri = new List<Loc>();
			for (var i = 1; i <= 18; i++)
			{
				listaLocuri.Add(new Loc(i));
			}
			
			//ia lista cu rezervari din db
			var listaRezervari = ServiceRezervari.FindAll();

			//ocupa locurile incepand cu primul
			var locCurent = 0;
			listaRezervari.ToList().ForEach(x =>
			{
				//daca exista rezervare, cauta clientul si asigneaza locul
				if (x.IdCursa == id)
				{
					var clientCurent = ServiceClienti.FindClient(x.IdClient);
					var nrLocuriDeAdaugat = x.NrLocuri;
					while (nrLocuriDeAdaugat > 0)
					{
						listaLocuri[locCurent].Nume = clientCurent.Nume;
						listaLocuri[locCurent].Prenume = clientCurent.Prenume;
						locCurent++;
						nrLocuriDeAdaugat--;
					}
					
				}
				
			});

			return listaLocuri;
		}

		public static IEnumerable<Destinatie> FindAllRezervatii()
		{
			return ServiceDestinatie.FindAllDestinatii();
		}

		public static Cursa CautaCursaDupaDetalii(DateTime dataCautare, Destinatie destinatieCautare)
		{
			var listaCursa = ServiceCursa.FindAllCurse();
			var cursa = default(Cursa);
			listaCursa.ToList().ForEach(x =>
			{
				if (x.IdDestinatie == destinatieCautare.Id && x.DataPlecare.CompareTo(dataCautare) == 0)
				{
					cursa = x;
				}
			});
			return cursa;
		}


		/// <summary>
		/// Functie care adauga o noua rezervare 
		/// </summary>
		/// <param name="cursaCurenta">Cursa la care se adauga rezervarea</param>
		/// <param name="numeAdaugare">Nume client</param>
		/// <param name="prenumeAdaugare">Prenume client</param>
		/// <param name="numarLocuriAdaugare">Numar locuri</param>
		internal static void AdaugaRezervare(Cursa cursaCurenta, string numeAdaugare, string prenumeAdaugare, int numarLocuriAdaugare)
		{
			//prima data cautam clientul in db dupa nume si prenume, daca nu exista adaugam unul nou
			var client = ServiceClienti.FindClinetDupaDetalii(numeAdaugare, prenumeAdaugare);
			if (client == null)
			{
				var clientDeAdaugat = new Client(DbRepoUtils.getMaxId(ServiceClienti.FindAllClienti()), numeAdaugare, prenumeAdaugare);
				ServiceClienti.addClient(clientDeAdaugat);
				client = clientDeAdaugat;
			}

			//verificam ca cursa sa aibe un numar de locuri mai mare sau egal e cat cel rezervat
			if (cursaCurenta.NrLocuriDisponibile <= numarLocuriAdaugare)
			{
				throw new ServiceException("Nu exista suficiete locuri disponibile!");
			}

			//modificam cursa la noul numarde locuri
			ServiceCursa.UpdateCursa(new Cursa(cursaCurenta.Id, cursaCurenta.Destinatie, cursaCurenta.DataPlecare, cursaCurenta.NrLocuriDisponibile - numarLocuriAdaugare, cursaCurenta.LocPlecare));

			//aduagam rezervarea
			var idRez = DbRepoUtils.getMaxId(ServiceRezervari.FindAll());
			var rezervareDeAdaugat = new Rezervare(idRez,client.Id,cursaCurenta.Id,numarLocuriAdaugare);
			ServiceRezervari.AddRezervare(rezervareDeAdaugat);
	
		}

		
	}
}