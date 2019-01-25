using System.Collections.Generic;
using System.Web.Http;
using System.Web.Http.Cors;
using RestWepApiMppCurse.Models;
using RestWepApiMppCurse.Service;

namespace RestWepApiMppCurse.Controllers
{
	[EnableCors(origins: "*", headers: "*", methods: "*")]
	public class CurseController : ApiController
    {
        // GET: api/Curse
        public IEnumerable<Cursa> Get()
        {
	        return ServiceCursa.FindAllCurse();
        }

        // GET: api/Curse/5
        public Cursa Get(int id)
        {
	        return ServiceCursa.FindOne(id);
        }

        // POST: api/Curse
        public void Post([FromBody]Cursa value)
        {
	        ServiceCursa.AddCursa(value);
        }

        // PUT: api/Curse/5
        public void Put(int id, [FromBody]Cursa value)
        {
			ServiceCursa.UpdateCursa(value);
        }

        // DELETE: api/Curse/5
        public void Delete(int id)
        {
			ServiceCursa.DeleteCursa(id);
        }
    }
}
