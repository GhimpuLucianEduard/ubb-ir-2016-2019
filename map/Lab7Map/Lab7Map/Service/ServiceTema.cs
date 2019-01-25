using System.Collections.Generic;
using Lab7Map.Domain;
using Lab7Map.Repository;

namespace Lab7Map.Service
{
    public class ServiceTema
    {
        private IRepository<int, Tema> repo;

        public ServiceTema(IRepository<int, Tema> repo)
        {
            this.repo = repo;
        }

        public Tema addTema(int id, string info, int deadline)
        {
            return repo.Save(new Tema(id, info, deadline));
        }

        public Tema UpdateTema(int id, string info, int deadline)
        {
            return repo.Update(new Tema(id, info, deadline));
        }

        public Tema DeleteTema(int id)
        {
            return repo.Delete(id);
        }

        public Tema FindTema(int id)
        {
            return repo.Find(id);
        }

        public List<Tema> GetAllTeme()
        {
            return repo.FindAll();
        }

    }
}