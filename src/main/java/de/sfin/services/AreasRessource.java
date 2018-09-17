package de.sfin.services;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.sfin.model.Areas;
import de.sfin.model.ResponseDTO;
import de.sfin.repository.AreasRepository;

@Path("/areas")
public class AreasRessource {
	
	AreasRepository areasRepo = new AreasRepository();
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Areas> getAllCharter() {
		return areasRepo.getAreas();
	}

	@POST
	@Path("/add/new")
	public Response addNewCharter(@FormParam("area") String area, @FormParam("subAreas") List<String> subAreas) {
		ResponseDTO response = areasRepo.addAreas(area, subAreas);
		return Response.status(response.getReturnCode()).entity(response.getMessageText()).build();
	}

}
