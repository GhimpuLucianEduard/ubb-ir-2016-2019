using System;
using System.Collections.Generic;
using CurseMPP.Models;

namespace CurseMPP.Repository
{
	public abstract class AbstractRepoDB<ID, T> : IRepo<ID, T> where T : HasId<ID>
	{

		IDictionary<ID, T> items;
		private IValidator<T> validator;

		public AbstractRepoDB(IValidator<T> vali)
		{
			items = new Dictionary<ID, T>();
			validator = vali;
			loadData();

		}


		public virtual void delete(ID id)
		{	
			
			items.Remove(id);

		}

		public IEnumerable<T> FindAll()
		{
			return items.Values;
		}

		public T find(ID id)
		{

			if (items.ContainsKey(id))
				return items[id];
			else
				return default(T);

		}

		public virtual void save(T entity)
		{
			if (!items.ContainsKey(entity.Id))
			{	
				validator.validate(entity);
				items.Add(entity.Id, entity);
			}
				
			else throw new RepositoryException("Duplicate entity " + entity);
		}

		public virtual void update(T entity)
		{
			if (!items.ContainsKey(entity.Id))
			{
				throw new RepositoryException("Nu exista o astfel de intrare!");
			}
			else
			{
				validator.validate(entity);
				items[entity.Id]=entity;
			}
		}


		public abstract void loadData();

	}
}