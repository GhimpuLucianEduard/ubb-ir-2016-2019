using System;
using System.CodeDom;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Configuration;
using System.Data.SQLite;
using CurseMPP.Models;
using CurseMPP.Repository;
using CurseMPP.Utils;

namespace CurseMPP
{
	class Program
	{
		static void Main(string[] args)
		{
			//XmlConfigurator.Configure(new System.IO.FileInfo(args[0]));


			IRepo<int,Destinatie> repo = new RepoDestinatii(new ValidatorDestinatie());
			var toti = repo.FindAll();
			foreach (var destinatie in toti)
			{
				Console.WriteLine(destinatie.ToString());
			}
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			repo.save(new Destinatie(9999,"jamaica"));
			toti = repo.FindAll();
			foreach (var destinatie in toti)
			{
				Console.WriteLine(destinatie.ToString());
			}
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			repo.update(new Destinatie(9999,"jamaica22222222"));
			toti = repo.FindAll();
			foreach (var destinatie in toti)
			{
				Console.WriteLine(destinatie.ToString());
			}
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");

			repo.delete(9999);
			toti = repo.FindAll();
			foreach (var destinatie in toti)
			{
				Console.WriteLine(destinatie.ToString());
			}
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			IRepo<int,Personal> repoPers = new RepoPersonal(new ValidatorPersonal());
			repoPers.save(new Personal(1,"admin1","admin","ion1","popescu1"));
			repoPers.save(new Personal(2,"admin2","admin","ion2","popescu2"));
			repoPers.save(new Personal(3,"admin3","admin","ion3","popescu3"));
			foreach (var personal in repoPers.FindAll())
			{
				Console.WriteLine(personal.ToString());
			}
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			repoPers.update(new Personal(2,"update","update","update","update"));
			foreach (var personal in repoPers.FindAll())
			{
				Console.WriteLine(personal.ToString());
			}
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			repoPers.delete(1);
			repoPers.delete(2);
			repoPers.delete(3);
			foreach (var personal in repoPers.FindAll())
			{
				Console.WriteLine(personal.ToString());
			}
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			repo.delete(455);
			repo.save(new Destinatie(455, "j4314a"));
			IRepo<int,Cursa> repoCurse = new RepoCurse(new ValidatorCursa());
			repoCurse.save(new Cursa(10,455,DateTime.Now, 10));
			repoCurse.save(new Cursa(11,455,DateTime.Now, 10));
			repoCurse.save(new Cursa(12,455,DateTime.Now, 10));
			foreach (var cursa in repoCurse.FindAll())
			{
				Console.WriteLine(cursa.ToString());
			}
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			repoCurse.update(new Cursa(12, 455, DateTime.Now, 0));
			foreach (var cursa in repoCurse.FindAll())
			{
				Console.WriteLine(cursa.ToString());
			}
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			repoCurse.delete(10);
			repoCurse.delete(11);
			repoCurse.delete(12);
			foreach (var cursa in repoCurse.FindAll())
			{
				Console.WriteLine(cursa.ToString());
			}
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			IRepo<int,Client> repoClienti= new RepoClienti(new ValidatorClient());
			repoClienti.save(new Client(90,"ion","ionescu"));
			repoClienti.save(new Client(91,"ion","ionescu"));
			repoClienti.save(new Client(92,"ion","ionescu"));
			repoClienti.save(new Client(93,"ion","ionescu"));
			foreach (var client in repoClienti.FindAll())
			{
				Console.WriteLine(client.ToString());
			}
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			repoClienti.update(new Client(90,"update,","update"));
			foreach (var client in repoClienti.FindAll())
			{
				Console.WriteLine(client.ToString());
			}
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			repoClienti.delete(90);
			repoClienti.delete(91);
			repoClienti.delete(92);
			repoClienti.delete(93);
			foreach (var client in repoClienti.FindAll())
			{
				Console.WriteLine(client.ToString());
			}
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			IRepo<int,Rezervare> repoRez = new RepoRezervare(new ValidatorRezervare());
			repoClienti.delete(872);
			repoClienti.save(new Client(872,"da","da"));

			repo.delete(455);
			repo.save(new Destinatie(455, "j4314a"));

			repoCurse.delete(100);
			repoCurse.save(new Cursa(100, 455, DateTime.Now, 10));

			repoRez.save(new Rezervare(10,872, 100, 10));
			repoRez.save(new Rezervare(11,872, 100, 3));
			repoRez.save(new Rezervare(12,872, 100, 2));

			foreach (var rezervare in repoRez.FindAll())
			{
				Console.WriteLine(rezervare.ToString());
			}

			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");


			repoRez.update(new Rezervare(11,872, 100, 5));
			foreach (var rezervare in repoRez.FindAll())
			{
				Console.WriteLine(rezervare.ToString());
			}



			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			repoRez.delete(10);
			repoRez.delete(11);
			repoRez.delete(12);

			foreach (var rezervare in repoRez.FindAll())
			{
				Console.WriteLine(rezervare.ToString());
			}
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");
			Console.WriteLine("============================================================");


		}
	}
}
