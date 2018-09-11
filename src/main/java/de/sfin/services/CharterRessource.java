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

@Path("/charter")
public class CharterRessource {

  CharterRepository charterRepo = new CharterRepository();

  @GET
  @Path("/all/{status}")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Charter> getAllCharter(@PathParam("status") String status) {
    Charter.Status charterStatus = Charter.Status.valueOf(status);
    return charterRepo.getCharters(charterStatus);
  }



}
