package de.sfin.services;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import java.util.List;

import io.jsondb.JsonDBTemplate;

import de.sfin.connector.*;
import de.sfin.model.*;

@Path("/tester")
public class TesterService {

  @GET
  @Path("/all")
  public List<Tester> getAllTester(){
    JsonDBConnector jsondbConn = new JsonDBConnector();
    JsonDBTemplate jsonDbTemplate = jsondbConn.createJsonDBTemplate();
    List<Tester> testers = jsonDbTemplate.findAll(Tester.class);
    // String output = "Tester Bob, tester@bob.de; Jonny Tester, jonny@test.de";
    // return Response.status(200).entity(output).build();

    if(testers.isEmpty()){
      return null;
    }
    System.out.println("first tester in the list: "+testers.get(0));
    return testers;
  }

  @POST
  @Path("/add/{firstname}/{lastname}/{email}")
  public Response addTester(@PathParam("firstname") String firstname,@PathParam("lastname") String lastname, @PathParam("email") String email){
    JsonDBConnector jsondbConn = new JsonDBConnector();
    JsonDBTemplate jsonDbTemplate = jsondbConn.createJsonDBTemplate();
    jsonDbTemplate.createCollection(Tester.class);

    Tester tester = new Tester();
    tester.setId();
    tester.setFirstname(firstname);
    tester.setLastname(lastname);
    tester.setEmail(email);
    jsonDbTemplate.insert(tester);
    String output = "add Tester: "+tester.getFirstname()+" "+tester.getLastname();
    return Response.status(200).entity(output).build();
  }


}
