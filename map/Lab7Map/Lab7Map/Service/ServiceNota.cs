using System;
using System.Collections.Generic;
using Lab7Map.Domain;
using Lab7Map.Repository;

namespace Lab7Map.Service
{
    public class ServiceNota
    {
        private IRepository<string, Nota> repo;

        public ServiceNota(IRepository<string, Nota> repo)
        {
            this.repo = repo;
        }

        public Nota AddNota(string idStud, int idTema, double valoare, int saptPred)
        {
            return repo.Save(new Nota(idStud, idTema, valoare, saptPred));
        }

        public Nota UpdateNota(string idStud, int idTema, double valoare, int saptPred)
        {
            return repo.Update(new Nota(idStud, idTema, valoare, saptPred));
        }

        public Nota DeleteNota(string id)
        {
            return repo.Delete(id);
        }

        public Nota FindNota(string id)
        {
            return repo.Find(id);
        }

        public List<Nota> GetAllNote()
        {
            return repo.FindAll();
        }
    }
}