using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography.X509Certificates;
using System.Text;
using System.Threading.Tasks;

namespace cheltu
{
    class Program
    {
        static void Main(string[] args)
        {
            List<Cheltuiala> rez = new List<Cheltuiala>();
            string[] lines =
                System.IO.File.ReadAllLines(
                    "c:\\users\\deus\\documents\\visual studio 2015\\Projects\\cheltu\\cheltu\\cheltuieli.txt");

            foreach (string line in lines)
            {

                try
                {
                    string[] separatedFields = line.Split(';');
                    //hrana;100;mcdols;Darius;2000-12-10;
                    string desc = separatedFields[2];
                    string efec = separatedFields[3];
                    string t = separatedFields[0];
                    double sum = Convert.ToDouble(separatedFields[1]);
                    DateTime dt = Convert.ToDateTime(separatedFields[4]);
                    TipCheltu tp;
                    tp = (TipCheltu) Enum.Parse(typeof(TipCheltu), t);
                    Cheltuiala r = new Cheltuiala(desc, efec, tp, sum, dt);
                    rez.Add(r);
                }
                catch (ApplicationException e)
                {
                    Console.WriteLine(e.Message);
                }

                

            }
            rez.ForEach(x => Console.WriteLine(x.ToString()));

            string tipcmd = Console.ReadLine();
            TipCheltu tp2;
            tp2 = (TipCheltu)Enum.Parse(typeof(TipCheltu), tipcmd);
            double suma1 = 0;
            
            List<Pair> membrii = new List<Pair>();
            foreach (Cheltuiala cheltuiala in rez)
            {   Pair p = new Pair(cheltuiala.efectuatDe,0);
                bool gasit = false;
                membrii.ForEach(pp =>
                {
                    if (pp.nume == cheltuiala.efectuatDe)
                    {
                        gasit = true;
                    }
                });
                if (gasit == false)
                {
                    membrii.Add(p);
                }
            }

            membrii.ForEach(x =>
            {
                rez.ForEach(c2=>
                {
                    if ((c2.tip == tp2) && (c2.efectuatDe.CompareTo(x.nume)==0))
                    {
                        x.val += c2.suma;
                    }
                });
            });

            membrii.ForEach(x=>Console.WriteLine("user"+":"+x.nume+"suma:"+x.val));
            


        }
    }
}
