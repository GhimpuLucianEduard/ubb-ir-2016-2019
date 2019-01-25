using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;
using DomainLib.Model;

namespace REST.Controllers
{
    public class PunctsController : ApiController
    {
        private CercetasiEntities db = new CercetasiEntities();

        // GET: api/Puncts
        public IQueryable<Punct> GetPuncts()
        {
            return db.Puncts;
        }

        // GET: api/Puncts/5
        [ResponseType(typeof(Punct))]
        public IHttpActionResult GetPunct(int id)
        {
            Punct punct = db.Puncts.Find(id);
            if (punct == null)
            {
                return NotFound();
            }

            return Ok(punct);
        }

        // PUT: api/Puncts/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutPunct(int id, Punct punct)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != punct.Id)
            {
                return BadRequest();
            }

            db.Entry(punct).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!PunctExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return StatusCode(HttpStatusCode.NoContent);
        }

        // POST: api/Puncts
        [ResponseType(typeof(Punct))]
        public IHttpActionResult PostPunct(Punct punct)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.Puncts.Add(punct);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = punct.Id }, punct);
        }

        // DELETE: api/Puncts/5
        [ResponseType(typeof(Punct))]
        public IHttpActionResult DeletePunct(int id)
        {
            Punct punct = db.Puncts.Find(id);
            if (punct == null)
            {
                return NotFound();
            }

            db.Puncts.Remove(punct);
            db.SaveChanges();

            return Ok(punct);
        }

	    [Route("api/Puncts/{id}/copii")]
	    public IEnumerable<Copil> GetCopils(int id)
	    {
		    var puncte = db.Puncts.Find(id);
			var rez = new List<Copil>();
		    db.Treceres.ToList().ForEach(x =>
		    {
			    if (x.NrPunct.Equals(puncte.Id))
			    {	
					var copil = new Copil()
					{
						Id = x.Copil.Id,
						Nume = x.Copil.Nume
					};
				    rez.Add(copil);
			    }
		    });

			return rez;
	    }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool PunctExists(int id)
        {
            return db.Puncts.Count(e => e.Id == id) > 0;
        }
    }
}