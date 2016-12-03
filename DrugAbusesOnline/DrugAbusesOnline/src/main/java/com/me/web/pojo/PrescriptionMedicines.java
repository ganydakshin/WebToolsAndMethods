package com.me.web.pojo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.*;

@Entity
@Table(name="PRESCRIPTION_MEDICINES")

public class PrescriptionMedicines {

	@Id
	@GeneratedValue
	@Column(name="id", unique=true, nullable=false)
	private int id;
	
	private int prescription;
	
	private int medicineId;
	
	@Column(name="quantity")
	private int quantity;
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public PrescriptionMedicines() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	
	public int getPrescription() {
		return prescription;
	}

	public void setPrescription(int prescription) {
		this.prescription = prescription;
	}

	/**
	 * @return the medicineId
	 */
	public int getMedicineId() {
		return medicineId;
	}

	/**
	 * @param medicineId the medicineId to set
	 */
	public void setMedicineId(int medicineId) {
		this.medicineId = medicineId;
	}

	
}
