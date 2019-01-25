using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using Newtonsoft.Json.Serialization;

namespace ClientTestCurse
{
	public class TestServiceConsumer
	{
		private HttpClient _httpClient;
		public Dictionary<long?, Cursa> Dictionary { get; set; }
		private string _baseUri = "http://localhost:52008/api/Curse";	

		public TestServiceConsumer()
		{
			Dictionary = new Dictionary<long?, Cursa>();
			_httpClient = new HttpClient();
		}

		public async Task GetAll()
		{	
			Dictionary.Clear();
			var uri = new Uri(_baseUri);
			var request = new HttpRequestMessage(HttpMethod.Get, uri);
			var response = await _httpClient.SendAsync(request);

			if (response.IsSuccessStatusCode)
			{
				Console.WriteLine("Am adus cursele din api....");
				var body = await response.Content.ReadAsStringAsync();
				var curse = (JArray) JsonConvert.DeserializeObject(body);
				foreach (var jToken in curse)
				{
					var cursa = JsonConvert.DeserializeObject<Cursa>(jToken.ToString());
					Dictionary[cursa.Id] = cursa;
					Console.WriteLine(cursa);
				}
			}
			else
			{
				Console.WriteLine("A aparut o eroare...");
			}
		}

		public async Task Delete(long? id)
		{
			var uri = new Uri(_baseUri + "/" + id);
			var request = new HttpRequestMessage(HttpMethod.Delete, uri);
			var response = await _httpClient.SendAsync(request);

			if (response.IsSuccessStatusCode)
			{
				Console.WriteLine("Am sters cursa cu id " + id +" din api....");
				await DisplayCurseFromApi();
			}
			else
			{
				Console.WriteLine("A aparut o eroare...");
			}
		}

		public async Task Add(Cursa cursa) 
		{
			var uri = new Uri(_baseUri);
			var request = new HttpRequestMessage(HttpMethod.Post, uri);
			var json = JsonConvert.SerializeObject(cursa,
				new JsonSerializerSettings {ContractResolver = new CamelCasePropertyNamesContractResolver()});
			request.Content = new StringContent(json, Encoding.UTF8, "application/json");

			var response = await _httpClient.SendAsync(request);

			if (response.IsSuccessStatusCode)
			{
				Console.WriteLine("Am saduagat o noua cursa in api...." + cursa.ToString());
				await DisplayCurseFromApi();
			}
			else
			{
				Console.WriteLine("A aparut o eroare...");
			}
		}

		public async Task Update(Cursa cursa)
		{
			var uri = new Uri(_baseUri + "/" + cursa.Id);
			var request = new HttpRequestMessage(HttpMethod.Put, uri);
			var json = JsonConvert.SerializeObject(cursa,
				new JsonSerializerSettings { ContractResolver = new CamelCasePropertyNamesContractResolver() });
			request.Content = new StringContent(json, Encoding.UTF8, "application/json");

			var response = await _httpClient.SendAsync(request);

			if (response.IsSuccessStatusCode)
			{
				Console.WriteLine("Am smodificat cursa din api...." + cursa.ToString());
				await DisplayCurseFromApi();
			}
			else
			{
				Console.WriteLine("A aparut o eroare...");
			}
		}

		public async Task DisplayCurseFromApi()
		{
			await GetAll();
			foreach (var keyValuePair in Dictionary)
			{
				Console.WriteLine(keyValuePair.Value.ToString());
			}
		}
	}
}