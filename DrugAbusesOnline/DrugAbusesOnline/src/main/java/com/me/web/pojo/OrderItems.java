package com.me.web.pojo;
import javax.persistence.*;

@Entity
@Table(name="orderItems")
public class OrderItems {

	@Id
	@GeneratedValue
	@Column(name="orderItemNo",unique=true, nullable=false)
	private int orderItemNo;
	
	@OneToOne(fetch=FetchType.EAGER)
	@PrimaryKeyJoinColumn(name="medicineId")
	private Medicine medicine;

	public int getOrderItemNo() {
		return orderItemNo;
	}


	public void setOrderItemNo(int orderItemNo) {
		this.orderItemNo = orderItemNo;
	}
	@Column(name="quntity")
	private int quantity;
	
	
	private int order;
	
	public OrderItems() {
		
		
	}
	

	



	/**
	 * @return the medicine
	 */
	public Medicine getMedicine() {
		return medicine;
	}


	/**
	 * @param medicine the medicine to set
	 */
	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}


	/**
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}


	/**
	 * @param order the order to set
	 */
	public void setOrder(int order) {
		this.order = order;
	}


	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
}
