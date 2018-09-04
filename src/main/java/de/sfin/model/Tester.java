package de.sfin.model;

import java.util.UUID;

import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;

@Document(collection = "tester", schemaVersion= "1.0")
public class Tester {

  @Id
  private String id;
  private String firstname;
  private String lastname;
  private String email;


  /**
  * Default Tester constructor
  */
  public Tester (String firstname, String lastname, String email) {
    this.id = UUID.randomUUID().toString();
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
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
	public void setId() {
		this.id = UUID.randomUUID().toString();
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

}
