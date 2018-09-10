package de.sfin.repository;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import de.sfin.connector.JsonDBConnector;
import de.sfin.model.Tester;
import io.jsondb.JsonDBTemplate;

public class TesterRepository{
	
	private final static Logger LOG = Logger.getLogger(TesterRepository.class.getName());
	
	public List<Tester> getAllTesters() {
		JsonDBConnector jsondbConn = new JsonDBConnector();
	    JsonDBTemplate jsonDbTemplate = jsondbConn.createJsonDBTemplate();
	    List<Tester> testers = jsonDbTemplate.findAll(Tester.class);

	    if(testers.isEmpty()){
	    	LOG.warning("No Tester found in 'JsonDB'");
	      return null;
	    }

	    return testers;
	}
	
	public String addTester(String firstname, String lastname, String email) {
		JsonDBConnector jsondbConn = new JsonDBConnector();
		JsonDBTemplate jsonDbTemplate = jsondbConn.createJsonDBTemplate();
		String output = "";
		Boolean collectionExists = jsonDbTemplate.collectionExists(Tester.class);
		if (!collectionExists) {
			jsonDbTemplate.createCollection(Tester.class);
			Tester tester = new Tester();
			tester.setId("1");
			tester.setFirstname(firstname);
			tester.setLastname(lastname);
			tester.setEmail(email);
			jsonDbTemplate.insert(tester);
			LOG.info("insert Tester: " + tester.toString());
			output = "insert Tester: " + tester.toString();
		} else {
			Tester tester = new Tester();
			Random random = new Random();
			tester.setId(String.valueOf(jsonDbTemplate.getCollection(Tester.class).size() + random.nextInt(1000)));
			tester.setFirstname(firstname);
			tester.setLastname(lastname);
			tester.setEmail(email);
			jsonDbTemplate.upsert(tester);
			LOG.info("upsert Tester: " + tester.toString());
			output = "upsert Tester: " + tester.toString();
		}
		return output;
	}
	
	public String removeTester(String ids) {
		JsonDBConnector jsondbConn = new JsonDBConnector();
		JsonDBTemplate jsonDbTemplate = jsondbConn.createJsonDBTemplate();
		String output = "";
		String[] testerIds = ids.split(",");
		for (String id : testerIds) {
			Tester tester = new Tester();
			tester.setId(id);
			jsonDbTemplate.remove(tester, Tester.class);
			LOG.info("Tester with id " + id + " was removed.");
			output.concat("Tester with id " + id + " was removed. ");
		}
		return output;
	}
	
	
	
}