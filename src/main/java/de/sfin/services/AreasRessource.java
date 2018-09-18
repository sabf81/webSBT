package de.sfin.services;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.inject.Singleton;

import de.sfin.model.Areas;
import de.sfin.model.ResponseDTO;
import de.sfin.repository.AreasRepository;

@Singleton
@Path("/areas")
public class AreasRessource {
	
	AreasRepository areasRepo = new AreasRepository();
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Areas> getAllCharter() {
		return areasRepo.getAreas();
	}
	
	@GET
	@Path("/{areaid}/subareas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getSubAreas(@PathParam("areaid") String areaid){
		return areasRepo.getSubAreas(areaid);
	}

	@POST
	@Path("/add/new")
	public Response addNewCharter(@FormParam("area") String area, @FormParam("subAreas") List<String> subAreas) {
		ResponseDTO response = areasRepo.addAreas(area, subAreas);
		return Response.status(response.getReturnCode()).entity(response.getMessageText()).build();
	}
	
	@PUT
	@Path("/update/{id}")
	public Response updateCharter(@PathParam("id") String id,@FormParam("subAreas") List<String> subAreas) {
		ResponseDTO response= areasRepo.updateArea(id,subAreas);
		return Response.status(response.getReturnCode()).entity(response.getMessageText()).build();
		
	}

}
