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
    class XMLRepoStudent : AbstractXMLRepo<string,Student>
    {
        public XMLRepoStudent(IValidator<Student> vali, string fileName) : base(vali, fileName)
        {
        }

        public override IEnumerable<Student> GetData()
        {
            XDocument xdoc = XDocument.Load(FileName);

            IEnumerable<Student> studentLst = from obj in xdoc.Descendants("Student")
                select new Student
                {
                    id = obj.Attribute("id").Value,
                    Nume = obj.Element("Nume").Value,
                    Grupa = Convert.ToInt32(obj.Element("Grupa").Value),
                    Email = obj.Element("Mail").Value,
                    Prof = obj.Element("Prof").Value,
                };
            return studentLst;
        }

        

        public override void WriteData()
        {   
            XDocument xdoc = XDocument.Load(FileName);
            
            var xEle = new XElement("Studenti",
                from entity in FindAll()
                select new XElement("Student",
                    new XAttribute("id", entity.id),
                    new XElement("Nume", entity.Nume ),
                    new XElement("Grupa", entity.Grupa),
                    new XElement("Mail", entity.Email),
                    new XElement("Prof", entity.Prof)
                ));
                 
            xdoc.Element("Studenti").ReplaceWith(xEle);
            xdoc.Save(FileName);

        }

        public override void WriteElement(Student entity)
        {
            XDocument xdoc = XDocument.Load(FileName);

            var xEle =  new XElement("Student",
                    new XAttribute("id", entity.id),
                    new XElement("Nume", entity.Nume),
                    new XElement("Grupa", entity.Grupa),
                    new XElement("Mail", entity.Email),
                    new XElement("Prof", entity.Prof)
                );

            xdoc.Element("Studenti").Add(xEle);
            xdoc.Save(FileName);
        }
    }
}
