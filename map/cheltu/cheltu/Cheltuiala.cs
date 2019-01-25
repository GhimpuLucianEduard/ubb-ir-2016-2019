using System;

namespace cheltu
{
    public class Cheltuiala
    {
       
        public string id { get; set; }
        public string desc { get; set; }
        public string efectuatDe { get; set; }
        public TipCheltu tip { get; set; }
        public double suma { get; set; }
        public DateTime data { get; set; }

        public Cheltuiala(string desc, string efectuatDe, TipCheltu tip, double suma, DateTime data)
        {
            this.desc = desc;
            this.efectuatDe = efectuatDe;
            this.tip = tip;
            this.suma = suma;
            this.data = data;
            this.id = efectuatDe + data;
        }

        public override string ToString()
        {
            return suma + efectuatDe + data + tip + desc;
        }
    }
}