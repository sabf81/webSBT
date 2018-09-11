package de.sfin.repository;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import de.sfin.connector.JsonDBConnector;
import de.sfin.model.Charter;
import io.jsondb.JsonDBTemplate;

public class CharterRepository{

	private final static Logger LOG = Logger.getLogger(CharterRepository.class.getName());

  public List<Charter> getCharters(Charter.Status status){
    JsonDBConnector jsondbConn = new JsonDBConnector();
      JsonDBTemplate jsonDbTemplate = jsondbConn.createJsonDBTemplate();
      List<Charter> charterList = jsonDbTemplate.findAll(Charter.class);

      if(charterList.isEmpty()){
        LOG.warning("No Charter found in 'JsonDB'");
        return null;
      }

      List<Charter> charterWithStatus = new ArrayList();
      for(Charter ch : charterList){
        if(ch.getStatus().equals(status)){
          charterWithStatus.add(ch);
        }
      }
      return charterWithStatus;
  }

}
