using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml;
using System.Xml.Linq;
using Lab7Map.Domain;
using Lab7Map.Domain.Validators;
using Lab7Map.Repository;
using Lab7Map.Repository.XMLRepo;
using Lab7Map.Service;
using Lab7Map.UI;

namespace Lab7Map
{
    class Program
    {
        static void Main(string[] args)
        {
            
            ServiceStudent st = new ServiceStudent(new XMLRepoStudent(new ValidatorStudent(), "C:\\Users\\Deus\\documents\\visual studio 2015\\Projects\\Lab7Map\\Lab7Map\\XMLdata\\Studenti.xml"));
            ServiceTema tm = new ServiceTema(new XMLRepoTema(new ValidatorTema(), "C:\\Users\\Deus\\documents\\visual studio 2015\\Projects\\Lab7Map\\Lab7Map\\XMLdata\\Teme.xml"));
            ServiceNota nt = new ServiceNota(new XMLRepoNota(new ValidatorNota(), "C:\\Users\\Deus\\documents\\visual studio 2015\\Projects\\Lab7Map\\Lab7Map\\XMLdata\\Note.xml"));
            ServiceCatalog catalog = new ServiceCatalog(st,tm,nt);
            var ui = new UI.UI(catalog);
            ui.runMainMenu();



         


        }
    }
}
