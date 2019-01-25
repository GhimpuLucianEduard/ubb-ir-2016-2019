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
    class XMLRepoTema : AbstractXMLRepo<int,Tema>
    {
        public XMLRepoTema(IValidator<Tema> vali, string fileName) : base(vali, fileName)
        {
        }

        public override void WriteData()
        {
            XDocument xdoc = XDocument.Load(FileName);

            var xEle = new XElement("Teme",
                from entity in FindAll()
                select new XElement("Tema",
                    new XAttribute("id", entity.id),
                    new XElement("Info", entity.Info),
                    new XElement("Deadline", entity.Deadline)
                ));

            xdoc.Element("Teme").ReplaceWith(xEle);
            xdoc.Save(FileName);
        }

        public override void WriteElement(Tema entity)
        {
            XDocument xdoc = XDocument.Load(FileName);

            var xEle = new XElement("Tema",
                new XAttribute("id", entity.id),
                new XElement("Info", entity.Info),
                new XElement("Deadline", entity.Deadline)
                
            );

            xdoc.Element("Teme").Add(xEle);
            xdoc.Save(FileName);
        }

        public override IEnumerable<Tema> GetData()
        {
            XDocument xdoc = XDocument.Load(FileName);

            IEnumerable<Tema> listTema = from obj in xdoc.Descendants("Tema")
                select new Tema
                {
                    id = Convert.ToInt32(obj.Attribute("id").Value),
                    Info = obj.Element("Info").Value,
                    Deadline = Convert.ToInt32(obj.Element("Deadline").Value)
                 
                };
            return listTema;
        }

        

    }
}
