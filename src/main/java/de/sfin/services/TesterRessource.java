package de.sfin.services;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import java.util.List;
import java.io.IOException;

import de.sfin.model.*;
import de.sfin.repository.*;

@Path("/tester")
public class TesterRessource {
	
	TesterRepository testerRepo = new TesterRepository();

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Tester> getAllTester() {
		return testerRepo.getAllTesters();
	}

	@POST
	@Path("/add")
	public Response addTester(@FormParam("inputFirstNameOfTester") String firstname,
			@FormParam("inputLastNameOfTester") String lastname, @FormParam("inputTesterEmail") String email,
			@Context HttpServletResponse servletResponse) throws IOException {
		String output = testerRepo.addTester(firstname, lastname, email);
		servletResponse.sendRedirect("/configure.html#NewTester");
		return Response.status(200).entity(output).build();
	}


	@DELETE
	@Path("/remove/{ids}")
	public Response removeTester(@PathParam("ids") String ids) {
		String output = testerRepo.removeTester(ids);
		return Response.status(200).entity(output).build();
	}

	

}
