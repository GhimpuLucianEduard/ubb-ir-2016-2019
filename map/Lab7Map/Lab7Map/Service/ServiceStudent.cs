using System;
using System.Collections.Generic;
using Lab7Map.Domain;
using Lab7Map.Repository;

namespace Lab7Map.Service
{
    public class ServiceStudent
    {
        private IRepository<string, Student> repo;

        public ServiceStudent(IRepository<string, Student> repo)
        {
            this.repo = repo;
        }

        public Student AddStudent(string id, string nume, int grupa, string email, string prof)
        {
            return repo.Save(new Student(id, nume, grupa, email, prof));
        }

        public Student FindStudent(string id)
        {
            return repo.Find(id);
        }

        public List<Student> GetAllStudenti()
        {
            return repo.FindAll();
        }

        public Student DeleteStudent(string id)
        {
            return repo.Delete(id);
        }

        public Student UpdateStudent(string id, string nume, int grupa, string email, string prof)
        {
            return repo.Update(new Student(id, nume, grupa, email, prof));
        }

    }

}