package com.me.web.pojo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="PERSON")
@Inheritance(strategy=InheritanceType.JOINED)

public class Person {
	
	@Id
	@GeneratedValue
	@Column(name="personId", unique=true, nullable=false)
	private int personId;
	
	@Column(name="personName")
	private String personName;
	
	@Column(name="age")
	private int age;
	
	@Column(name="userName")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="type")
	private String type;
	
	@Column(name="email")
	private String email;
	
	public Person() {
		
	}
	

		
	/**
	 * @return the listOfOrder
	 */


	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	/**
	 * @return the personId
	 */
	public int getPersonId() {
		return personId;
	}



	/**
	 * @param personId the personId to set
	 */
	public void setPersonId(int personId) {
		this.personId = personId;
	}



	/**
	 * @return the personName
	 */
	public String getPersonName() {
		return personName;
	}


	/**
	 * @param personName the personName to set
	 */
	public void setPersonName(String personName) {
		this.personName = personName;
	}


	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
