﻿using System.ComponentModel;
using PropertyChanged;

namespace LabMppCurseWPF.ViewModels
{	
	/// <summary>
	/// Clasa de baza pentru orice ViewModel
	/// </summary>
	public class BasicViewModel : INotifyPropertyChanged
	{
		/// <summary>
		/// eventul are loc cand o propietate din clasa se schimba
		/// </summary>
		public event PropertyChangedEventHandler PropertyChanged = (sender, e) => { };
	}
}