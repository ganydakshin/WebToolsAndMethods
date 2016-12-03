package com.me.web.pojo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import antlr.collections.List;

@Entity
@Table(name="Patient")
@PrimaryKeyJoinColumn(name="personId")

public class Patient extends Person {
	
//	@OneToMany(fetch=FetchType.LAZY, mappedBy="patient")
//	private Set<Order> listOfOrder = new HashSet<Order>();
//	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="patient")
	private Set<Prescription> listOfPrescriptions = new HashSet<Prescription>();
	
	@Column(name="bloodGroup")
	private String bloodGroup;
	
	@Column(name="weight")
	private float weight;
	
	@Column(name="bloodPressure")
	private float bloodPressure;
	
	@Column(name="lastVisitDate")
	private String lastVisitDate;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="patient")
	private Set<Appointment> listOfAppointments = new HashSet<Appointment>();
	
	public Set<Appointment> getListOfAppointments() {
		return listOfAppointments;
	}





	public void setListOfAppointments(Set<Appointment> listOfAppointments) {
		this.listOfAppointments = listOfAppointments;
	}





	public Patient() {
	//	this.listOfOrder = new HashSet<Order>();
		this.listOfPrescriptions = new HashSet<Prescription>();
	}
	
	



	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
//	public Set<Order> getListOfOrder() {
//		return listOfOrder;
//	}
//
//
//	/**
//	 * @param listOfOrder the listOfOrder to set
//	 */
//	public void setListOfOrder(Set<Order> listOfOrder) {
//		this.listOfOrder = listOfOrder;
//	}


	/**
	 * @return the listOfPrescriptions
	 */
	public Set<Prescription> getListOfPrescriptions() {
		return listOfPrescriptions;
	}


	/**
	 * @param listOfPrescriptions the listOfPrescriptions to set
	 */
	public void setListOfPrescriptions(Set<Prescription> listOfPrescriptions) {
		this.listOfPrescriptions = listOfPrescriptions;
	}

	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public float getBloodPressure() {
		return bloodPressure;
	}
	public void setBloodPressure(float bloodPressure) {
		this.bloodPressure = bloodPressure;
	}
	public String getLastVisitDate() {
		return lastVisitDate;
	}
	public void setLastVisitDate(String lastVisitDate) {
		this.lastVisitDate = lastVisitDate;
	}
		
}
