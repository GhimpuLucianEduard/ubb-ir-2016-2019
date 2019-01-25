using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography.X509Certificates;
using System.Text;
using System.Threading.Tasks;
using System.Xml;
using System.Xml.Linq;
using Lab7Map.Domain;
using Lab7Map.Domain.Validators;

namespace Lab7Map.Repository.XMLRepo
{
    abstract class AbstractXMLRepo<ID,E> : AbstractRepository<ID,E>, IXMLRepo<ID,E> where E : IHasId<ID>
    {
        public string FileName { get; set; }

        public AbstractXMLRepo(IValidator<E> vali, string fileName) : base(vali)
        {
            FileName = fileName;
            LoadData();
            
        }

        public  void LoadData()
        {
            GetData().ToList().ForEach(x=>base.Save(x));
        }

        public abstract void WriteData();

        public abstract void WriteElement(E entity);

        public abstract IEnumerable<E> GetData();

        public override E Save(E entity)
        {
            E aux = base.Save(entity);
            if (aux==null)
            {
                WriteElement(entity);
            }
            return aux;
        }

        

        public override E Update(E entity)
        {
            E aux = base.Update(entity);
            if (aux == null)
            {
                WriteData();
            }
            return aux;

        }

        public override E Delete(ID id)
        {
            E aux = base.Delete(id);
            WriteData();
            return aux;
        }
    }
}
