using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Data.SQLite;
using System.Net.Mime;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Forms;
using System.Windows.Input;
using CurseMPP.Models;
using LabMppCurseWPF.Commands;
using LabMppCurseWPF.Models;
using LabMppCurseWPF.View;
using Application = System.Windows.Application;
using MessageBox = System.Windows.Forms.MessageBox;

namespace LabMppCurseWPF.ViewModels
{		
	/// <summary>
	/// Viewmodel care se ocupa de curse
	/// </summary>
	public class ViewModelMain : BasicViewModel
	{

		private ServiceClient _service;

		/// <summary>
		/// Constructor cu toate comenzile necesare si alte asignari
		/// </summary>
		public ViewModelMain(ServiceClient srv)
		{	

			_service = srv;
			_service.setViewModel(this);
			this.Curse = new ObservableCollection<Cursa>(_service.FindAllCurseForView());
			this.CommandSelectionChanged = new BasicCommandWithParameter(GenereazaLocuri);
			this.Destinatii = new ObservableCollection<Destinatie>(_service.FindAllDestinatii());
			this.CommandCautare = new BasicCommand(CautaCursa);
			this.CommandAdaugaRezervare = new BasicCommand(AdaugaRezervare);
			this.NumarLocuri = new ObservableCollection<int>(new List<int>());
			this.LogoutCommand = new BasicCommand(Logout);
			for (var i = 1; i <= 18; i++)
			{
				NumarLocuri.Add(i);
			}
		}


		public void UpdateAction()
		{
			//TODO sa sterg functia asta si sa adaug, sterg etc... direct pe listele observabile
			this.Curse = new ObservableCollection<Cursa>(_service.FindAllCurseForView());
			//MessageBox.Show("Am fost notificat","Am fost notificat",MessageBoxButtons.OK);
		}

		#region Commands

		/// <summary>
		/// Command pentru evenimentul de selectie
		/// </summary>
		public ICommand CommandSelectionChanged { get; set; }

		/// <summary>
		/// Command pentru cautare cursa
		/// </summary>
		public ICommand CommandCautare { get; set; }

		/// <summary>
		/// Command pentru adaugare rezervare
		/// </summary>
		public ICommand CommandAdaugaRezervare { get; set; }

		/// <summary>
		/// Commanda pentru logout
		/// </summary>
		public ICommand LogoutCommand { get; set; }

		#endregion

		#region Propietati de bind

		/// <summary>
		/// Lista de curse
		/// </summary>
		public ObservableCollection<Cursa> Curse { get; set; }

		/// <summary>
		/// Cursa curent ala care se executa operatii
		/// </summary>
		public Cursa CursaCurenta { get; set; }

		/// <summary>
		/// Lista cu locuri 
		/// </summary>
		public ObservableCollection<Loc> Locuri { get; set; }

		/// <summary>
		/// Lista cu destinatii
		/// </summary>
		public ObservableCollection<Destinatie> Destinatii { get; set; }

		/// <summary>
		/// Data folosita pentru a cuata o cursa
		/// </summary>
		public DateTime DataCautare { get; set; }

		/// <summary>
		/// Destinatie folosita pentru a cauta o cursa
		/// </summary>
		public Destinatie DestinatieCautare { get; set; }

		/// <summary>
		/// Numele clientului pentru o rezervare de adaugat
		/// </summary>
		public string NumeAdaugare { get; set; }

		/// <summary>
		/// Prenumele clientului pentru o rezervare de adaugat
		/// </summary>
		public string PrenumeAdaugare { get; set; }

		/// <summary>
		/// Numarul de locuri pentru o rezervare de adaugat
		/// </summary>
		public int NumarLocuriAdaugare { get; set; }

		/// <summary>
		/// O simpla lista pentru a afisa numerele de la 1 la 18 pentru locuri
		/// </summary>
		public ObservableCollection<int> NumarLocuri { get; set; }

		#endregion

		#region Actiuni

		/// <summary>
		/// Actiunea care genereaza lista de locuri pe baza unei curse
		/// </summary>
		/// <param name="param">Cursa</param>
		public void GenereazaLocuri(object param)
		{
			var cursa = (Cursa)param;
			//asta se face doar cand selectia a fost schimbata in mod voluntar
			//nu se schimba cand selectia se schimba din cuaza altor evenimente externe
			if (cursa != null)
			{
				var listaLocuri = _service.GenereazaLocuriPentruCursa(cursa.Id);
				this.Locuri = new ObservableCollection<Loc>(listaLocuri);
			}
			CursaCurenta = cursa;


		}

		/// <summary>
		/// Actiunea care cauta o cursa, afiseaza mesaje de eroare daca e nevoie
		/// </summary>
		public void CautaCursa()
		{
			if (DataCautare == null || DestinatieCautare == default(Destinatie))
			{
				MessageBox.Show("Alege data si destinatia!", "Warning", MessageBoxButtons.OK, MessageBoxIcon.Warning,
					MessageBoxDefaultButton.Button1);
			}
			else
			{
				var cursa = _service.CautaCursaDupaDetalii(DataCautare, DestinatieCautare);
				if (cursa != null)
				{
					GenereazaLocuri(cursa);
					CursaCurenta = cursa;
				}
				else
				{
					MessageBox.Show("Nu exista o astfel de cursa!", "Warning", MessageBoxButtons.OK, MessageBoxIcon.Warning,
						MessageBoxDefaultButton.Button1);
				}
			}

		}

		/// <summary>
		/// Actiunea pentru adaugarea unei rezervari
		/// </summary>
		private void AdaugaRezervare()
		{
			if (string.IsNullOrWhiteSpace(NumeAdaugare) || string.IsNullOrWhiteSpace(PrenumeAdaugare) ||
			    NumarLocuriAdaugare.CompareTo(null) == 0)
			{
				MessageBox.Show("Campuri necompletate!", "Warning", MessageBoxButtons.OK, MessageBoxIcon.Warning,
					MessageBoxDefaultButton.Button1);
			}
			else
			{
				try
				{
					_service.AdaugaRezervare(CursaCurenta.Id, NumeAdaugare, PrenumeAdaugare, NumarLocuriAdaugare);
					MessageBox.Show("Rezervare adaugata!", "Info", MessageBoxButtons.OK, MessageBoxIcon.None,
						MessageBoxDefaultButton.Button1);
				}
				catch (Exception e)
				{
					MessageBox.Show(e.Message, "Warning", MessageBoxButtons.OK, MessageBoxIcon.Warning,
						MessageBoxDefaultButton.Button1);
				}
			}
			//UpdateAction();

		}

		/// <summary>
		/// actiunea de executat la logout
		/// </summary>
		private void Logout()
		{
			var win = new ViewLogin();
			win.Show();
			Application.Current.MainWindow.Close();
			Application.Current.MainWindow = win;
		}


		#endregion

	}
}