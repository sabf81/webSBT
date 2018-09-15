package de.sfin.repository;

import java.util.*;
import java.util.logging.Logger;

import de.sfin.connector.JsonDBConnector;
import de.sfin.model.Areas;
import io.jsondb.JsonDBTemplate;

public class AreasRepsository {
	
	private final static Logger LOG = Logger.getLogger(AreasRepsository.class.getName());
	
	public List<String> getCategories(){
		JsonDBConnector jsondbConn = new JsonDBConnector();
		JsonDBTemplate jsonDbTemplate = jsondbConn.createJsonDBTemplate();		
		List<Areas> charterList = jsonDbTemplate.findAll(Areas.class);
		return null;
		
		
	}

}
