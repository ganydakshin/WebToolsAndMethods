package com.me.web.pojo;
import javax.persistence.*;

@Entity
@Table(name="PHARMACY_MEDICINES")
public class PharmacyMedicines {

	@Id
	@GeneratedValue
	@Column(name="medicinePharmacy", unique=true, nullable=false)
	private int id;
	
	private int personId;
	
	
	private int medicineId;
	
	@Column(name="quantity")
	private int quantity;
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the pharmacy
	 */
	public int getMedicineId() {
		return medicineId;
	}
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	/**
	 * @param medicineId the medicineId to set
	 */
	public void setMedicineId(int medicineId) {
		this.medicineId = medicineId;
	}
	
}
