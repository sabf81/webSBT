package de.sfin.services;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import java.util.List;
import java.util.Random;
import java.io.IOException;

import io.jsondb.JsonDBTemplate;

import de.sfin.connector.*;
import de.sfin.model.*;

@Path("/tester")
public class TesterService {

  @GET
  @Path("/all")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Tester> getAllTester(){
    JsonDBConnector jsondbConn = new JsonDBConnector();
    JsonDBTemplate jsonDbTemplate = jsondbConn.createJsonDBTemplate();
    List<Tester> testers = jsonDbTemplate.findAll(Tester.class);

    if(testers.isEmpty()){
      return null;
    }

    return testers;
  }

  @POST
  // @Path("/add/{firstname}/{lastname}/{email}")
  @Path("/add")
  // public Response addTester(@PathParam("firstname") String firstname,@PathParam("lastname") String lastname, @PathParam("email") String email){
    public Response addTester(@FormParam("inputFirstNameOfTester") String firstname,
    @FormParam("inputLastNameOfTester") String lastname,
    @FormParam("inputTesterEmail") String email, @Context HttpServletResponse servletResponse) throws IOException{
    JsonDBConnector jsondbConn = new JsonDBConnector();
    JsonDBTemplate jsonDbTemplate = jsondbConn.createJsonDBTemplate();
    String output = "";
    int sizeOfCollection = jsonDbTemplate.getCollection(Tester.class).size();
    if(sizeOfCollection==0) {
      jsonDbTemplate.createCollection(Tester.class);
      Tester tester = new Tester();
      tester.setId("1");
      tester.setFirstname(firstname);
      tester.setLastname(lastname);
      tester.setEmail(email);
      jsonDbTemplate.insert(tester);
      output = "insert Tester: "+tester.toString();
    } else{
      Tester tester = new Tester();
      Random random = new Random();
      tester.setId(String.valueOf(sizeOfCollection+random.nextInt(1000)));
      tester.setFirstname(firstname);
      tester.setLastname(lastname);
      tester.setEmail(email);
      jsonDbTemplate.upsert(tester);
      output = "upsert Tester: "+tester.toString();
    }
    servletResponse.sendRedirect("/configure.html");
    return Response.status(200).entity(output).build();
  }


}
