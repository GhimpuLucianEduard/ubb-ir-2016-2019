using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace examneMap
{
    class Program
    {
        static void Main(string[] args)
        {
            //  CITIREA DIN FISIER
            List<Mesaj> rez = new List<Mesaj>();
            string[] lines = System.IO.File.ReadAllLines(
                //schimbi calea
                "c:\\users\\deus\\documents\\visual studio 2015\\Projects\\examneMap\\examneMap\\mesaje.txt");

            foreach (string line in lines)
            {

                try
                {   
                    string[] separatedFields = line.Split(';');
                    //construiesti ce ai nevoie
                    string exp = separatedFields[0];
                    string mesaj = separatedFields[1];
                    string cui = separatedFields[3];
                    DateTime dt = Convert.ToDateTime(separatedFields[2]);
                    Mesaj m = new Mesaj( mesaj,exp, dt, cui);
                    rez.Add(m);
                }
                catch (ApplicationException e)
                {
                    Console.WriteLine(e.Message);
                }

            }

            //verificarea daca s-au adaugat in lista
            Console.WriteLine("================LISTA MESAJELOR=========================");
            rez.ForEach(x=>Console.WriteLine(x.ToString()));


            //CALCULUL PROPRIU ZIS
            Console.WriteLine("================DATA=========================");
            Console.Write("tipareste luna: ");
            int month = int.Parse(Console.ReadLine());
            Console.Write("tipareste ziua: ");
            int day = int.Parse(Console.ReadLine());
            Console.Write("tipareste anul: ");
            int year = int.Parse(Console.ReadLine());
            
            DateTime inputtedDate = new DateTime(year, month, day);

            Console.WriteLine(inputtedDate);

            Dictionary<string,int> frecvInts = new Dictionary<string, int>();

            rez.ForEach(m =>
            {
                if (m.data.CompareTo(inputtedDate) == 0) 
                {
                    if (!frecvInts.ContainsKey(m.expediatar))
                    {
                        frecvInts.Add(m.expediatar,1);
                    }
                    else
                    {
                        frecvInts[m.expediatar]++;
                    }
                }
            });


            Console.WriteLine("================TOP EXPEDIATARI=========================");
            var sortedDict = from entry in frecvInts orderby entry.Value descending select entry;
                        foreach (KeyValuePair<string, int> kvp in sortedDict)
                        {
                            
                            Console.WriteLine("Key = {0}, Value = {1}", kvp.Key, kvp.Value);
                        }

        }
    }

}