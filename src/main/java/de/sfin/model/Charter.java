package de.sfin.model;

import javax.xml.bind.annotation.XmlRootElement;

import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;

@Document(collection = "Charter", schemaVersion= "1.0")
@XmlRootElement
public class Charter {

  public enum Status {
    TODO, READY_TO_REVIEW, APPROVED
  }

  @Id
  private String id;
  private String Chartername;
  private Status Status;

  /**
	* Returns value of Status
	* @return
	*/
	public enum getStatus() {
		return Status;
	}

	/**
	* Sets new value of Status
	* @param
	*/
	public void setStatus(enum Status) {
		this.Status = Status;
	}

	/**
	* Returns value of id
	* @return
	*/
	public String getId() {
		return id;
	}

	/**
	* Sets new value of id
	* @param
	*/
	public void setId(String id) {
		this.id = id;
	}

	/**
	* Returns value of Chartername
	* @return
	*/
	public String getChartername() {
		return Chartername;
	}

	/**
	* Sets new value of Chartername
	* @param
	*/
	public void setChartername(String Chartername) {
		this.Chartername = Chartername;
	}

	/**
	* Returns value of Status
	* @return
	*/
	public Status getStatus() {
		return Status;
	}

	/**
	* Sets new value of Status
	* @param
	*/
	public void setStatus(Status Status) {
		this.Status = Status;
	}


	/**
	* Create string representation of Charter for printing
	* @return
	*/
	@Override
	public String toString() {
		return "Charter [Status=" + Status + ", id=" + id + ", Chartername=" + Chartername + ", Status=" + Status + "]";
	}
}
