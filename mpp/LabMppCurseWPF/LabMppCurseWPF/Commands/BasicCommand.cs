﻿using System;
using System.Windows.Input;

namespace LabMppCurseWPF.Commands
{	
	/// <summary>
	/// Comanda de baza care executa o actiune
	/// </summary>
	public class BasicCommand : ICommand
	{	
		/// <summary>
		/// Actiunea de executat
		/// </summary>
		private Action _action;


		/// <summary>
		/// Eventul care are loc cand CanExecute se schimba
		/// </summary>
		public event EventHandler CanExecuteChanged = (sender, e) => { };

		#region ICommand

		/// <summary>
		/// Constructor
		/// </summary>
		/// <param name="action">Actiunea de executat</param>
		public BasicCommand(Action action)
		{
			_action = action;
		}

		/// <summary>
		/// Basic command poate fi executat oricand
		/// </summary>
		/// <param name="parameter"></param>
		/// <returns></returns>
		public bool CanExecute(object parameter)
		{
			return true;
		}

		/// <summary>
		/// Executa actiunea data
		/// </summary>
		/// <param name="parameter"></param>
		public void Execute(object parameter)
		{
			_action();
		}

		#endregion
	}
}