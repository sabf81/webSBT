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
	private String area;
	private List<String> subAreas;
	
	
	public Areas(){
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public List<String> getSubAreas() {
		return subAreas;
	}
	public void setSubAreas(List<String> subAreas) {
		this.subAreas = subAreas;
	}

	@Override
	public String toString() {
		return "Areas [id=" + id + ", area=" + area + ", subAreas=" + subAreas + "]";
	}
	
}
