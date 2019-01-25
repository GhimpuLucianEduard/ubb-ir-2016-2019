using System;
using System.Linq;
using Lab7Map.Domain.Validators;
using Lab7Map.Service;

namespace Lab7Map.UI
{
    public class UiCommands
    {
        private ServiceCatalog service;

        public UiCommands(ServiceCatalog service)
        {
            this.service = service;
        }

        public void AddStudent()
        {
            try
            {
                Console.WriteLine("Introdu id student:");
                string id = Console.ReadLine();
                Console.WriteLine("Introdu numele studentului:");
                string nume = Console.ReadLine();
                Console.WriteLine("Introdu grupa studentului:");
                int grupa;
   
                if (!Int32.TryParse(Console.ReadLine(), out grupa))
                {
                    Console.WriteLine("TASTEAZA UN NUMAR!");
                }

                Console.WriteLine("Introdu email:");
                string email = Console.ReadLine();
                Console.WriteLine("Introdu profesorul de laborator:");
                string prof = Console.ReadLine();

                service.serviceStudens.AddStudent(id, nume, grupa, email, prof);
                Console.WriteLine("Student adaugat cu succes!");

            }
            catch (ValidationException e)
            {
                Console.WriteLine(e.Message);
            }
        }

        public  void getAllStudenti()
        {
           
            service.serviceStudens.GetAllStudenti().ForEach(x=>Console.WriteLine(x.ToString()));
        }

        public void DeleteStudent()
        {

            Console.WriteLine("Introdu id-ul studentului de sters:");
            string id = Console.ReadLine();
            service.serviceStudens.DeleteStudent(id);
            Console.WriteLine("Studentu a fost sters!");

        }

        public void UpdateStudent()
        {
            try
            {
                Console.WriteLine("Introdu id student:");
                string id = Console.ReadLine();
                Console.WriteLine("Introdu numele studentului:");
                string nume = Console.ReadLine();
                Console.WriteLine("Introdu grupa studentului:");
                int grupa;

                if (!Int32.TryParse(Console.ReadLine(), out grupa))
                {
                    Console.WriteLine("TASTEAZA UN NUMAR!");
                }

                Console.WriteLine("Introdu email:");
                string email = Console.ReadLine();
                Console.WriteLine("Introdu profesorul de laborator:");
                string prof = Console.ReadLine();

                service.serviceStudens.UpdateStudent(id, nume, grupa, email, prof);
                Console.WriteLine("Student updatat succes!");

            }
            catch (ValidationException e)
            {
                Console.WriteLine(e.Message);
            }
        }

        public void AddTema()
        {
            try
            {
                
                Console.WriteLine("Introdu nr tema:");
                int id;

                if (!Int32.TryParse(Console.ReadLine(), out id))
                {
                    Console.WriteLine("TASTEAZA UN NUMAR!");
                }
                Console.WriteLine("Introdu descrierea:");
                string desc = Console.ReadLine();
                Console.WriteLine("Introdu deadlineul:");
                int deadline;

                if (!Int32.TryParse(Console.ReadLine(), out deadline))
                {
                    Console.WriteLine("TASTEAZA UN NUMAR!");
                }

                service.serviceTema.addTema(id, desc, deadline);
                Console.WriteLine("Tema adaugata cu succes!");

            }
            catch (ValidationException e)
            {
                Console.WriteLine(e.Message);
            }
        }

        public void GetAllTeme()
        {
            service.serviceTema.GetAllTeme().ForEach(x=>Console.WriteLine(x.ToString()));
        }

        public void deleteTema()
        {
            Console.WriteLine("Introdu id-ul temei de sters:");
            int id;

            if (!Int32.TryParse(Console.ReadLine(), out id))
            {
                Console.WriteLine("TASTEAZA UN NUMAR!");
            }

            service.serviceTema.DeleteTema(id);
            Console.WriteLine("Tema stersa cu succes!");

        }

        public void UpdateTema()
        {
            try
            {

                Console.WriteLine("Introdu nr tema:");
                int id;

                if (!Int32.TryParse(Console.ReadLine(), out id))
                {
                    Console.WriteLine("TASTEAZA UN NUMAR!");
                }
                Console.WriteLine("Introdu descrierea:");
                string desc = Console.ReadLine();
                Console.WriteLine("Introdu deadlineul:");
                int deadline;

                if (!Int32.TryParse(Console.ReadLine(), out deadline))
                {
                    Console.WriteLine("TASTEAZA UN NUMAR!");
                }

                service.serviceTema.UpdateTema(id, desc, deadline);
                Console.WriteLine("Tema modificata cu succes!");

            }
            catch (ValidationException e)
            {
                Console.WriteLine(e.Message);
            }
        }

        public void AddNota()
        {
            try
            {
                Console.WriteLine("Introdu id student:");
                string idStudent = Console.ReadLine();
                Console.WriteLine("Introdu id-ul temei:");
                int idTema;

                if (!Int32.TryParse(Console.ReadLine(), out idTema))
                {
                    Console.WriteLine("TASTEAZA UN NUMAR!");
                }

                Console.WriteLine("Introdu sapt predarii temei:");
                int predare;

                if (!Int32.TryParse(Console.ReadLine(), out predare))
                {
                    Console.WriteLine("TASTEAZA UN NUMAR!");
                }

                Console.WriteLine("Introdu valoare notei:");
                double valoare;

                if (!Double.TryParse(Console.ReadLine(), out valoare))
                {
                    Console.WriteLine("TASTEAZA UN NUMAR!");
                }

                service.serviceNota.AddNota(idStudent, idTema, valoare, predare);
                Console.WriteLine("Nota adaugata cu succes!");

            }
            catch (ValidationException e)
            {
                Console.WriteLine(e.Message);
            }
        }

        public void UpdateNota()
        {
            try
            {
                Console.WriteLine("Introdu id student:");
                string idStudent = Console.ReadLine();
                Console.WriteLine("Introdu grupa studentului:");
                int idTema;

                if (!Int32.TryParse(Console.ReadLine(), out idTema))
                {
                    Console.WriteLine("TASTEAZA UN NUMAR!");
                }

                Console.WriteLine("Introdu sapt predarii temei:");
                int predare;

                if (!Int32.TryParse(Console.ReadLine(), out predare))
                {
                    Console.WriteLine("TASTEAZA UN NUMAR!");
                }

                Console.WriteLine("Introdu valoare notei:");
                double valoare;

                if (!Double.TryParse(Console.ReadLine(), out valoare))
                {
                    Console.WriteLine("TASTEAZA UN NUMAR!");
                }

                service.serviceNota.UpdateNota(idStudent, idTema, valoare, predare);
                Console.WriteLine("Nota modificata cu succes!");

            }
            catch (ValidationException e)
            {
                Console.WriteLine(e.Message);
            }
        }

        public void DeleteNota()
        {
            Console.WriteLine("Introdu id-ul studentului:");
            string idStud = Console.ReadLine();
            Console.WriteLine("Introdu grupa studentului:");
            int idTema;

            if (!Int32.TryParse(Console.ReadLine(), out idTema))
            {
                Console.WriteLine("TASTEAZA UN NUMAR!");
            }

            service.serviceNota.DeleteNota(idStud + idTema);
            Console.WriteLine("Nota stearsa cu succes!");

        }

        public void GetAllNote()
        {
          service.serviceNota.GetAllNote().ForEach(x=>Console.WriteLine(x.ToString()));
        }

    }

}