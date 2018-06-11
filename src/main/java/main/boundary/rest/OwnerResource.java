package main.boundary.rest;

import main.boundary.rest.jwt.Secured;
import main.domain.Owner;
import main.domain.enums.AuthorizedApplications;
import main.service.OwnerService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Thom van de Pas on 16-5-2018
 */
@Path("owners")
@Stateless
public class OwnerResource {

    @Inject
    private OwnerService ownerService;

    /**
     * When an user wants to profile it posts it's first name, last name and its citizen service number.
     * The user object gets returned.
     *
     * @param owner is the owner that registers.
     * @return the found Owner.
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Secured(AuthorizedApplications.DRIVER)
    public Response getOwner(Owner owner) {
        if (owner == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        Owner foundOwner =
                this.ownerService.findByFullNameAndCSN(owner.getFirstName(),
                        owner.getLastName(), owner.getCitizenServiceNumber());
        if (foundOwner == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        if (!foundOwner.isRekeningRijder()) {
            foundOwner.setRekeningRijder(true);
            this.ownerService.createOrUpdate(foundOwner);
        }

        return Response.ok(foundOwner.toJson()).header("Access-Control-Allow-Origin", "*").build();

    }

}
