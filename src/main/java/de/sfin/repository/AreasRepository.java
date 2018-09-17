package de.sfin.repository;

import java.util.*;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;

import de.sfin.connector.JsonDBConnector;
import de.sfin.model.Areas;
import de.sfin.model.ResponseDTO;
import io.jsondb.JsonDBTemplate;

public class AreasRepository {
	
	private final static Logger LOG = Logger.getLogger(AreasRepository.class.getName());
	
	public List<Areas> getAreas(){
		JsonDBConnector jsondbConn = new JsonDBConnector();
		JsonDBTemplate jsonDbTemplate = jsondbConn.createJsonDBTemplate();		
		List<Areas> areaList = jsonDbTemplate.findAll(Areas.class);
		return areaList;
	}
	
	public ResponseDTO addAreas(String area, List<String> subAreas) {
		if (StringUtils.isBlank(area)) {
			LOG.warning("No Category found!");
			return new ResponseDTO(500,"Please enter a category");
			//TODO: Throw Exception
		}
		if (subAreas.size()==0) {
			LOG.warning("No Subcategory found!");
			return new ResponseDTO(500,"Please enter a Subcategory");
			//TODO: Throw Exception
		}
		JsonDBConnector jsondbConn = new JsonDBConnector();
		JsonDBTemplate jsonDbTemplate = jsondbConn.createJsonDBTemplate();
		Boolean collectionExists = jsonDbTemplate.collectionExists(Areas.class);
		int countOfEntries = jsonDbTemplate.getCollection(Areas.class).size();
		
		Areas areas = new Areas();
		areas.setArea(area);
		areas.setSubAreas(subAreas);
		Random random = new Random();
		areas.setId(String.valueOf(countOfEntries + random.nextInt(1000)));
		if(!collectionExists) {
			jsonDbTemplate.insert(areas);
			String insertOk = "insert Areas: " + areas.toString();
			LOG.info(insertOk);
			return new ResponseDTO(200,insertOk);
		}else {
			jsonDbTemplate.upsert(areas);
			String upsertOk = "upsert Areas: " + areas.toString();
			LOG.info(upsertOk);
			return new ResponseDTO(200,upsertOk);
		}
		
		
	}

}
