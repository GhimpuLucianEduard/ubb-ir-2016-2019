namespace Lab7Map.Service
{
    public class ServiceCatalog
    {
        public ServiceStudent serviceStudens { get; set; }
        public ServiceTema serviceTema { get; set; }
        public ServiceNota serviceNota { get; set;  }

        public ServiceCatalog(ServiceStudent serviceStudens, ServiceTema serviceTema, ServiceNota serviceNota)
        {
            this.serviceStudens = serviceStudens;
            this.serviceTema = serviceTema;
            this.serviceNota = serviceNota;
        }
    }
}