using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab7Map.Repository
{
    public interface IRepository<ID,E>
    {
        long Size();
        E Save(E entity);
        E Update(E entity);
        E Delete(ID id);
        E Find(ID id);
        List<E> FindAll();
    }
    
}
