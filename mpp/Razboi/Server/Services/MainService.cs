namespace Domain.Services
{
	public class MainService
	{
		public JucatorService JucatorSerice { get; set; }

		public MainService()
		{
			JucatorSerice = new JucatorService();
		}
	}
}