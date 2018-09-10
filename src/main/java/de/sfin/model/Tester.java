package de.sfin.model;

import javax.xml.bind.annotation.XmlRootElement;

import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;

@Document(collection = "Tester", schemaVersion= "1.0")
@XmlRootElement
public class Tester {

  @Id
  private String id;
  private String firstname;
  private String lastname;
  private String email;


  /**
  * Default Tester constructor
  */
  public Tester () {

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
	* Returns value of firstname
	* @return
	*/
	public String getFirstname() {
		return firstname;
	}

	/**
	* Sets new value of firstname
	* @param
	*/
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	* Returns value of lastname
	* @return
	*/
	public String getLastname() {
		return lastname;
	}

	/**
	* Sets new value of lastname
	* @param
	*/
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	* Returns value of email
	* @return
	*/
	public String getEmail() {
		return email;
	}

	/**
	* Sets new value of email
	* @param
	*/
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	* Create string representation of Tester for printing
	* @return
	*/
	@Override
	public String toString() {
		return "Tester [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + "]";
	}
}
