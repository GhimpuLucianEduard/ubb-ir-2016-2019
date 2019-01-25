using System;
using System.Collections.Generic;
using System.Runtime.InteropServices;
using Lab7Map.Service;

namespace Lab7Map.UI
{
    public class UI
    {
        public ServiceCatalog service { get; set; }
        private UiCommands cmds;
        private Dictionary<int,Delegate> CommandsMainMenu = new Dictionary<int, Delegate>();
        private Dictionary<int,Delegate> CommandsStudenti = new Dictionary<int, Delegate>();
        private Dictionary<int,Delegate> CommandsTeme = new Dictionary<int, Delegate>();
        private Dictionary<int,Delegate> CommandsNote = new Dictionary<int, Delegate>();

        public UI(ServiceCatalog service)
        {
            
            this.service = service;
            cmds = new UiCommands(service);
            setCommands();
        }

        private void ShowLogo()
        {
            Console.WriteLine("===========================================");
        }

        private void ShowMaiMenu()
        {
            ShowLogo();
            Console.WriteLine("Alegeti o optiune...");
            Console.WriteLine("1. Afisati menu Studenti");
            Console.WriteLine("2. Afisati menu Teme");
            Console.WriteLine("3. Afisati menu Note");
            Console.WriteLine("0. Exit");
            
        }

        private void ShowMenuStudenti()
        {
            Console.WriteLine("Alegeti o optiune...");
            Console.WriteLine("1. Afiseaza toti studenti");
            Console.WriteLine("2. Adauga un student");
            Console.WriteLine("3. Sterge un student");
            Console.WriteLine("4. Updateaza un student");
            Console.WriteLine("0. Exit");
        }
        private void ShowMenuTeme()
        {
            Console.WriteLine("Alegeti o optiune...");
            Console.WriteLine("1. Afiseaza toate temele");
            Console.WriteLine("2. Adauga o tema");
            Console.WriteLine("3. Sterge o tema");
            Console.WriteLine("4. Updateaza o tema");
            Console.WriteLine("0. Exit");
        }
        private void ShowMenuNote()
        {
            Console.WriteLine("Alegeti o optiune...");
            Console.WriteLine("1. Afiseaza toate notele");
            Console.WriteLine("2. Adauga o nota");
            Console.WriteLine("3. Sterge o nota");
            Console.WriteLine("4. Updateaza o nota");
            Console.WriteLine("0. Exit");
        }

        private void setCommands()
        {
            CommandsMainMenu.Add(1,new Action(runMenuStudenti));
            CommandsMainMenu.Add(2,new Action(runMenuTeme));
            CommandsMainMenu.Add(3,new Action(runMenuNote));

            CommandsStudenti.Add(1, new Action(cmds.getAllStudenti));
            CommandsStudenti.Add(2,new Action(cmds.AddStudent));
            CommandsStudenti.Add(3,new Action(cmds.DeleteStudent));
            CommandsStudenti.Add(4,new Action(cmds.UpdateStudent));
            
            CommandsTeme.Add(1, new Action(cmds.GetAllTeme));
            CommandsTeme.Add(2, new Action(cmds.AddTema));
            CommandsTeme.Add(3, new Action(cmds.deleteTema));
            CommandsTeme.Add(4, new Action(cmds.UpdateTema));

            CommandsNote.Add(1,new Action(cmds.GetAllNote));
            CommandsNote.Add(2,new Action(cmds.AddNota));
            CommandsNote.Add(3,new Action(cmds.DeleteNota));
            CommandsNote.Add(4,new Action(cmds.UpdateNota));
        }

        public void runMainMenu()
        {
            while (true)
            {
                ShowMaiMenu();
                string input = Console.ReadLine();
                int cmd;
                if (!Int32.TryParse(input, out cmd))
                {
                    Console.WriteLine("TASTEAZA UN NUMAR!");
                }
                else if (CommandsMainMenu.ContainsKey(cmd))
                {
                    Console.WriteLine("Ai ales: " + cmd);
                    Delegate aux = CommandsMainMenu[cmd];
                    aux.DynamicInvoke();
                }
                else if (cmd==0)
                {
                    break;
                }
                else
                {
                    Console.WriteLine("Comanda Invalida!");
                }
            }
        }

        public void runMenuStudenti()
        {
            while (true)
            {
                ShowMenuStudenti();
                string input = Console.ReadLine();
                int cmd;
                if (!Int32.TryParse(input, out cmd))
                {
                    Console.WriteLine("TASTEAZA UN NUMAR!");
                }
                else if (CommandsStudenti.ContainsKey(cmd))
                {
                    Console.WriteLine("Ai ales: " + cmd);
                    Delegate aux = CommandsStudenti[cmd];
                    aux.DynamicInvoke();
                }
                else if (cmd == 0)
                {
                    break;
                }
                else
                {
                    Console.WriteLine("Comanda Invalida!");
                }
            }
        }

        public void runMenuTeme()
        {
            while (true)
            {
                ShowMenuTeme();
                string input = Console.ReadLine();
                int cmd;
                if (!Int32.TryParse(input, out cmd))
                {
                    Console.WriteLine("TASTEAZA UN NUMAR!");
                }
                else if (CommandsTeme.ContainsKey(cmd))
                {
                    Console.WriteLine("Ai ales: " + cmd);
                    Delegate aux = CommandsTeme[cmd];
                    aux.DynamicInvoke();
                }
                else if (cmd == 0)
                {
                    break;
                }
                else
                {
                    Console.WriteLine("Comanda Invalida!");
                }
            }
        }

        public void runMenuNote()
        {
            while (true)
            {
                ShowMenuNote();
                string input = Console.ReadLine();
                int cmd;
                if (!Int32.TryParse(input, out cmd))
                {
                    Console.WriteLine("TASTEAZA UN NUMAR!");
                }
                else if (CommandsNote.ContainsKey(cmd))
                {
                    Console.WriteLine("Ai ales: " + cmd);
                    Delegate aux = CommandsNote[cmd];
                    aux.DynamicInvoke();
                }
                else if (cmd == 0)
                {
                    break;
                }
                else
                {
                    Console.WriteLine("Comanda Invalida!");
                }
            }
        }

    }
}