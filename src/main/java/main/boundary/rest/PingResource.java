package main.boundary.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("ping")
public class PingResource {


	@GET
	public String getPing(){
		return "AccountAdministrationSystem OK";
	}

	@GET
	@Path("/v2")
	public String getAdvancedPing(){return "pong";}
}
