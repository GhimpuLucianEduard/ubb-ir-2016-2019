using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab7Map.Domain
{
    public class Tema : IHasId<int>
    {
        public int id { get; set; }
        public string Info { get; set; }
        public int Deadline { get; set; }

        public Tema(int id, string info, int deadline)
        {
            this.id = id;
            Info = info;
            Deadline = deadline;
        }

        public Tema()
        {
            id = default(int);
            Info = default(string);
            Deadline = default(int);
        }

        public override string ToString()
        {
            return id + ";" + Info + ";" + Deadline + ";";
        }
    }
}
