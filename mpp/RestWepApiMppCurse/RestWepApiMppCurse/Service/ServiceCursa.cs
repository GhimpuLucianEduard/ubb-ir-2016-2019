using System.Collections;
using System.Collections.Generic;
using Newtonsoft.Json;
using Newtonsoft.Json.Serialization;
using RestWepApiMppCurse.Models;
using RestWepApiMppCurse.Repo;

namespace RestWepApiMppCurse.Service
{
	internal static class ServiceCursa
	{	
		private static DbRepoCursa _repository = new DbRepoCursa();

		private static JsonSerializerSettings _serializerSettings = new JsonSerializerSettings
			{ContractResolver = new CamelCasePropertyNamesContractResolver()};

		public static IEnumerable<Cursa> FindAllCurse()
		{
			return _repository.FindAll();
		}

		public static Cursa FindOne(int id)
		{	
			return _repository.Find(id);
		}

		public static void AddCursa(Cursa c)
		{
			_repository.Add(c);
		}

		public static void UpdateCursa(Cursa c)
		{
			_repository.Update(c);
		}

		public static void DeleteCursa(int id)
		{
			_repository.Delete(id);
		}
	}
}