using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using REST.Services;

namespace REST.Controllers
{
    public class IstoricJocController : ApiController
    {
		// GET: api/IstoricJoc/1/2
		[HttpGet]
		[Route("api/IstoricJoc/{idJoc}/{idJucator}")]
		public IstoricJoc Get(int idJoc, int idJucator)
        {
	        var srv = new RestService();
	        return srv.GetIstoricJoc(idJoc, idJucator);
        }

        // GET: api/IstoricJoc/5
        public string Get(int id)
        {
            return "value";
        }

        // POST: api/IstoricJoc
        public void Post([FromBody]string value)
        {
        }

        // PUT: api/IstoricJoc/5
        public void Put(int id, [FromBody]string value)
        {
        }

        // DELETE: api/IstoricJoc/5
        public void Delete(int id)
        {
        }
    }
}
