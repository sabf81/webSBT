package de.sfin.services;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.sfin.model.Charter;
import de.sfin.model.ResponseDTO;
import de.sfin.repository.CharterRepository;

@Path("/charter")
public class CharterRessource {

	CharterRepository charterRepo = new CharterRepository();

	@GET
	@Path("/all/{status}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Charter> getAllCharter(@PathParam("status") String status) {
		return charterRepo.getCharters(status);
	}

	@POST
	@Path("/add/new")
	public Response addNewCharter(@FormParam("charterName") String charterName) {
		ResponseDTO response = charterRepo.addNewCharter(charterName);
		return Response.status(response.getReturnCode()).entity(response.getMessageText()).build();
	}

	@DELETE
	@Path("/remove/{ids}")
	public Response removeCharter(@PathParam("ids") String ids) {
		ResponseDTO response = charterRepo.removeCharter(ids);
		return Response.status(response.getReturnCode()).entity(response.getMessageText()).build();
	}

}
