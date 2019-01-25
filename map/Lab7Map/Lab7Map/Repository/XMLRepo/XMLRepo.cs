using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab7Map.Repository.XMLRepo
{
    interface IXMLRepo<ID,E>
    {
        void LoadData();
        void WriteData();
        void WriteElement(E entity);
        
    }
}
