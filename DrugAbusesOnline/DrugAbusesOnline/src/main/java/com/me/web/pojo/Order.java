package com.me.web.pojo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="Order")

public class Order {

	@Id
	@GeneratedValue
	@Column(name="orderId",unique=true,nullable=false)
	private int orderId;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="order")
	private Set<OrderItems> listOfOrders = new HashSet<OrderItems>();
	
	private int patient;
	
	public Order() {
		
		this.listOfOrders = new HashSet<OrderItems>();
	}
	
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	
	
	/**
	 * @return the patient
	 */
	public int getPatient() {
		return patient;
	}

	/**
	 * @param patient the patient to set
	 */
	public void setPatient(int patient) {
		this.patient = patient;
	}

	/**
	 * @return the listOfOrders
	 */
	public final Set<OrderItems> getListOfOrders() {
		return listOfOrders;
	}

	/**
	 * @param listOfOrders the listOfOrders to set
	 */
	public final void setListOfOrders(Set<OrderItems> listOfOrders) {
		this.listOfOrders = listOfOrders;
	}
	
}
