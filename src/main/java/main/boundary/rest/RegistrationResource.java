package main.boundary.rest;

import main.domain.enums.AuthorizedApplications;
import main.service.TokenService;

import javax.inject.Inject;
import javax.json.Json;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("authenticate")
public class RegistrationResource {

	@Inject
	private TokenService tokenService;

	@GET
	@Path("{AppKey}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response AuthenticateApp(@PathParam("AppKey") String appKey) {

		for (AuthorizedApplications authorizedApplications : AuthorizedApplications.values()) {
			if (authorizedApplications.App().equals(appKey)) {
				return Response.ok(Json.createObjectBuilder().add("Token", tokenService.EncodeToken(appKey)).build()).header("Access-Control-Allow-Origin", "*").build();
			}
		}
		return Response.status(Response.Status.UNAUTHORIZED).header("Access-Control-Allow-Origin", "*").build();

	}
}
