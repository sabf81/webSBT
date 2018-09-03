package de.sfin.services;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/tester")
public class TesterService {

  @GET
  @Path("/all")
  public Response getAllTester(){

    String output = "Tester Bob, tester@bob.de; Jonny Tester, jonny@test.de";
    return Response.status(200).entity(output).build();
  }

  @POST
  @Path("/add/{firstname}/{lastname}")
  public Response addTester(@PathParam("firstname") String firstname,@PathParam("lastname") String lastname){

    String output = "Tester "+firstname+" "+lastname+" added!";
    return Response.status(200).entity(output).build();
  }


}
