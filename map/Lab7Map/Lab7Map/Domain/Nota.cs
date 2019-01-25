using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab7Map.Domain
{
    public class Nota : IHasId<String>
    {
        public string id { get; set; }
        public string IdStudent { get; set; }
        public int IdTema { get; set; }
        public double  Valoare { get; set; }
        public int SaptPredare { get; set; }

        public Nota(string idStudent, int idTema, double valoare, int saptPredare)
        {
            id = idStudent + idTema;
            IdStudent = idStudent;
            IdTema = idTema;
            Valoare = valoare;
            SaptPredare = saptPredare;
        }

//        public Nota()
//        {
//            id = default(string);
//            IdStudent = default(string);
//            IdTema = default(int);
//            Valoare = default(double);
//            SaptPredare = default(int);
//        }

        public override string ToString()
        {
            return id + ";" + Valoare + ";" + SaptPredare + ";";
        }
    }
}
