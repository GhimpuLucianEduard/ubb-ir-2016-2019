using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using REST.Models;
using REST.Service;

namespace REST.Controllers
{
    public class GamesController : ApiController
    {
//        // GET: api/Games
//        public IEnumerable<Game> Get()
//        {
//	        
//        }

        // GET: api/Games/5
        public Game Get(int id)
        {	
			var ctr = new GameService();
	        return ctr.GetJoc(id);
        }

        // POST: api/Games
        public void Post([FromBody]string value)
        {
        }

        // PUT: api/Games/5
        public void Put(int id, [FromBody]string value)
        {
        }

        // DELETE: api/Games/5
        public void Delete(int id)
        {
        }
    }
}
