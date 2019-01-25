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
using Domain.Models;

namespace REST.Controllers
{
    public class JocsController : ApiController
    {
        private RazboiEntities db = new RazboiEntities();
		
        // GET: api/Jocs
        public IQueryable<Joc> GetJocs()
        {
            return db.Jocs;
        }

        // GET: api/Jocs/5
        [ResponseType(typeof(Joc))]
        public IHttpActionResult GetJoc(int id)
        {
            Joc joc = db.Jocs.Find(id);
            if (joc == null)
            {
                return NotFound();
            }

            return Ok(joc);
        }

        // PUT: api/Jocs/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutJoc(int id, Joc joc)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != joc.Id)
            {
                return BadRequest();
            }

            db.Entry(joc).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!JocExists(id))
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

        // POST: api/Jocs
        [ResponseType(typeof(Joc))]
        public IHttpActionResult PostJoc(Joc joc)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.Jocs.Add(joc);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = joc.Id }, joc);
        }

        // DELETE: api/Jocs/5
        [ResponseType(typeof(Joc))]
        public IHttpActionResult DeleteJoc(int id)
        {
            Joc joc = db.Jocs.Find(id);
            if (joc == null)
            {
                return NotFound();
            }

            db.Jocs.Remove(joc);
            db.SaveChanges();

            return Ok(joc);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool JocExists(int id)
        {
            return db.Jocs.Count(e => e.Id == id) > 0;
        }
    }
}