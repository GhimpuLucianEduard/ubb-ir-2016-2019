using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Lab7Map.Domain;
using Lab7Map.Domain.Validators;

namespace Lab7Map.Repository
{
     abstract class AbstractRepository<ID, E> : IRepository<ID, E> where E : IHasId<ID>
    {
        private IDictionary<ID, E> entities = new Dictionary<ID, E>();
        private IValidator<E> vali;

        public AbstractRepository(IValidator<E> vali)
        {
            this.vali = vali;
        }

        public long Size()
        {
            return entities.Count();
        }

        virtual public E Save(E entity)
        {
            vali.Validate(entity);
            if (entities.ContainsKey(entity.id))
            {
                return entities[entity.id];
            }

            entities[entity.id] = entity;
            return default(E);
        }

        virtual public E Update(E entity)
        {
            vali.Validate(entity);
            if (entities.ContainsKey(entity.id))
            {
                entities[entity.id] = entity;
                return default(E);
            }

            return entity;
        }

        virtual public E Delete(ID id)
        {
            if (entities.ContainsKey(id))
            {
                var aux = Find(id);
                entities.Remove(id);
                return aux;
            }

            return default(E);
        }

        public E Find(ID id)
        {
            if (entities.ContainsKey(id))
            {
                return entities[id];
            }

            return default(E);
        }

        public List<E> FindAll()
        {
            return entities.Values.ToList();
        }

    }

}
