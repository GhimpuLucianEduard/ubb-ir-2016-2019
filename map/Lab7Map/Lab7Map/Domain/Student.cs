using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab7Map.Domain
{
    public class Student : IHasId<string>
    {
        public string id { get; set; }
        public string Nume { get; set; }
        public int Grupa { get; set; }
        public string Email { get; set; }
        public string Prof { get; set; }

        public Student(string id, string nume, int grupa, string email, string prof)
        {
            this.id = id;
            this.Nume = nume;
            this.Grupa = grupa;
            this.Email = email;
            this.Prof = prof;
        }

        public Student()
        {
            id = default(string);
            Nume = default(string);
            Grupa = default(int);
            Email = default(string);
            Prof = default(string);
        }


        public override string ToString()
        {
            return id + ";" + Nume + ";" + Grupa + ";" + Email + ";" + Prof + ";";
        }
    } 
}
