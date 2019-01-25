using System;
using System.Runtime.CompilerServices;

namespace examneMap
{
    public class Mesaj
    {
        public string mesaj { get; set; }
        public string expediatar { get; set; }
        public string cui { get; set; }
        public DateTime data { get; set; }

        public Mesaj(string mesaj, string expediatar, DateTime data, string cui)
        {
            this.mesaj = mesaj;
            this.expediatar = expediatar;
            this.data = data;
            this.cui = cui;
        }

        public override string ToString()
        {
            return this.expediatar + ":" + this.mesaj + ":" + this.data + ":" + this.cui + ":";
        }
    }
}