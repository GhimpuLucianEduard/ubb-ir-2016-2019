using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http.Headers;
using System.Web.Http;
using System.Web.Http.OData.Builder;
using System.Web.Http.OData.Extensions;
using DomainLib.Model;
using Newtonsoft.Json;
using Newtonsoft.Json.Serialization;

namespace REST
{
    public static class WebApiConfig
    {
        public static void Register(HttpConfiguration config)
        {
            // Web API configuration and services

            // Web API routes
            config.MapHttpAttributeRoutes();

            config.Routes.MapHttpRoute(
                name: "DefaultApi",
                routeTemplate: "api/{controller}/{id}",
                defaults: new { id = RouteParameter.Optional }
            );

	        config.Formatters.JsonFormatter.SupportedMediaTypes
		        .Add(new MediaTypeHeaderValue("text/html"));

	        config = GlobalConfiguration.Configuration;
			config.Formatters.JsonFormatter.SerializerSettings.ReferenceLoopHandling = ReferenceLoopHandling.Ignore;
	        config.Formatters.JsonFormatter.UseDataContractJsonSerializer = false;

		}
    }
}



