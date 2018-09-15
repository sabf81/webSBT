package de.sfin.model;

import javax.xml.bind.annotation.XmlRootElement;
import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;
import java.util.*;

@Document(collection = "Areas", schemaVersion= "1.0")
@XmlRootElement
public class Areas {
	
	@Id
	private String id;
	private String category;
	private List<String> areas;
	
	
	public Areas(){
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public List<String> getAreas() {
		return areas;
	}
	public void setAreas(List<String> areas) {
		this.areas = areas;
	}

	@Override
	public String toString() {
		return "Areas [id=" + id + ", category=" + category + ", areas=" + areas + "]";
	}
	
}
