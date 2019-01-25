using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Linq;
using Lab7Map.Domain;
using Lab7Map.Domain.Validators;

namespace Lab7Map.Repository.XMLRepo
{
    class XMLRepoNota : AbstractXMLRepo<string,Nota>
    {
        public XMLRepoNota(IValidator<Nota> vali, string fileName) : base(vali, fileName)
        {
        }

        public override void WriteData()
        {
            XDocument xdoc = XDocument.Load(FileName);

            var xEle = new XElement("Note",
                from entity in FindAll()
                select new XElement("Nota",
                    new XAttribute("id", entity.id),
                    new XElement("IdStudent", entity.IdStudent),
                    new XElement("IdTema", entity.IdTema),
                    new XElement("Valoare", entity.Valoare),
                    new XElement("SaptPredare", entity.SaptPredare)
                ));

            xdoc.Element("Note").ReplaceWith(xEle);
            xdoc.Save(FileName);
        }

        public override void WriteElement(Nota entity)
        {
            XDocument xdoc = XDocument.Load(FileName);

            var xEle = new XElement("Nota",
                new XAttribute("id", entity.id),
                new XElement("IdStudent", entity.IdStudent),
                new XElement("IdTema", entity.IdTema),
                new XElement("Valoare", entity.Valoare),
                new XElement("SaptPredare", entity.SaptPredare)
            );

            xdoc.Element("Note").Add(xEle);
            xdoc.Save(FileName);
        }

        public override IEnumerable<Nota> GetData()
        {
            XDocument xdoc = XDocument.Load(FileName);

            IEnumerable<Nota> listaTeme = from obj in xdoc.Descendants("Nota")
                select new Nota
                {
                    id = obj.Attribute("id").Value,
                    IdStudent = obj.Element("IdStudent").Value,
                    IdTema = Convert.ToInt32(obj.Element("IdTema").Value),
                    Valoare = Convert.ToDouble(obj.Element("Valoare").Value),
                    SaptPredare = Convert.ToInt32(obj.Element("SaptPredare").Value)
                    
                };
            return listaTeme;
        }
    }
}
