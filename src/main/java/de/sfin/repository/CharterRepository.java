package de.sfin.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;

import de.sfin.connector.JsonDBConnector;
import de.sfin.model.Charter;
import de.sfin.model.ResponseDTO;
import io.jsondb.JsonDBTemplate;

public class CharterRepository {

	private final static Logger LOG = Logger.getLogger(CharterRepository.class.getName());

	public List<Charter> getCharters(String status) {
		Charter.Status charterStatus = Charter.Status.valueOf(status);
		JsonDBConnector jsondbConn = new JsonDBConnector();
		JsonDBTemplate jsonDbTemplate = jsondbConn.createJsonDBTemplate();
		String jxQuery = String.format("/.[status='%s']", charterStatus);
		List<Charter> charterList = jsonDbTemplate.find(jxQuery,Charter.class);
		LOG.info("entries: "+charterList.size());
		if (charterList.isEmpty()) {
			LOG.warning("No Charter found in 'JsonDB'");
			return null;
		}

		return charterList;
	}

	public ResponseDTO addNewCharter(String charterName) {
		if (StringUtils.isBlank(charterName)) {
			LOG.severe("No Charter title found!");
			return new ResponseDTO(500,"Please enter a charter");
			//TODO: Throw Exception
		}
		JsonDBConnector jsondbConn = new JsonDBConnector();
		JsonDBTemplate jsonDbTemplate = jsondbConn.createJsonDBTemplate();
		Boolean collectionExists = jsonDbTemplate.collectionExists(Charter.class);
		int countOfEntries = jsonDbTemplate.getCollection(Charter.class).size();

		Charter charter = new Charter();
		charter.setChartername(charterName);
		charter.setStatus(Charter.Status.TODO);
		Random random = new Random();
		String output = "";
		charter.setId(String.valueOf(countOfEntries + random.nextInt(1000)));
		if(!collectionExists) {
			jsonDbTemplate.insert(charter);
			String insertOk = "insert Charter: " + charter.toString();
			LOG.info(insertOk);
			return new ResponseDTO(200,insertOk);

		}else {
			jsonDbTemplate.upsert(charter);
			String upsertOk = "upsert Charter: " + charter.toString();
			LOG.info(upsertOk);
			return new ResponseDTO(200,upsertOk);

		}

	}

	public ResponseDTO removeCharter(String ids){
		JsonDBConnector jsondbConn = new JsonDBConnector();
		JsonDBTemplate jsonDbTemplate = jsondbConn.createJsonDBTemplate();
		String output = "";
		String[] charterIds = ids.split(",");
		for (String id : charterIds) {
			Charter charter = new Charter();
			charter.setId(id);
			jsonDbTemplate.remove(charter, Charter.class);
			LOG.info("Charter with id " + id + " was removed.");
			output.concat("Charter with id " + id + " was removed. ");
		}
		return new ResponseDTO(200,output);
	}

}
