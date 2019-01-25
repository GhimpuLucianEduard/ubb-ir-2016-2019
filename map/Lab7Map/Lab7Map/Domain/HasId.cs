using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab7Map.Domain
{
    interface IHasId<ID>
    {
        ID id { get; set; }
    }
}
