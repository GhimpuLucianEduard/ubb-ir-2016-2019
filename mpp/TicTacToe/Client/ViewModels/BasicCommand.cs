using System;
using System.Windows.Input;

namespace Client.ViewModels
{
	public class BasicCommand : ICommand
	{
		private Action _action;
		public event EventHandler CanExecuteChanged = (sender, e) => { };

		#region ICommand

		public BasicCommand(Action action)
		{
			_action = action;
		}

		public bool CanExecute(object parameter)
		{
			return true;
		}

		public void Execute(object parameter)
		{
			_action();
		}

		#endregion
	}
}