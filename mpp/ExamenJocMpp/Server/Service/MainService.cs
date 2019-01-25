namespace Server.Service
{
	public class MainService
	{
		public JucatorService JucatorSerice { get; set; }
		public MutareService MutareService { get; set; }
		public JocService JocService { get; set; }

		public MainService()
		{
			JucatorSerice = new JucatorService();
			JocService = new JocService();
			MutareService = new MutareService();
		}
	}
}