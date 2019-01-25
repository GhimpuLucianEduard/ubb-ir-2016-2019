using System;
using System.Windows.Input;

namespace Client.ViewModels
{
	public class BasicCommandWithParameter : ICommand
	{
		private Action<object> _action;

		public event EventHandler CanExecuteChanged = (sender, e) => { };

		#region ICommand

		public BasicCommandWithParameter(Action<object> action)
		{
			_action = action;
		}

		public bool CanExecute(object parameter)
		{
			return true;
		}

		public void Execute(object parameter)
		{
			_action(parameter);
		}

		#endregion
	}
}