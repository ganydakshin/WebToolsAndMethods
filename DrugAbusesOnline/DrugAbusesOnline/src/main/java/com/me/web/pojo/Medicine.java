package com.me.web.pojo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="MEDICINE")
public class Medicine {

	@Id
	@GeneratedValue
	@Column(name="medicineId", unique=true, nullable=false)
	private int id;
	
	@Column(name="medicineName")
	private String medicineName;
	
	@Column(name="medicineQuantity")
	private String medicineQuantity;
	
	@Column(name="medicineProposition")
	private String medicineProposition;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="medicineId")
	private Set<PharmacyMedicines> listOfPharmacy = new HashSet<PharmacyMedicines>();
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="medicineId")
	private Set<PrescriptionMedicines> listOnPrescription = new HashSet<PrescriptionMedicines>();

//	@OneToOne(fetch=FetchType.EAGER,mappedBy="medicine", cascade=CascadeType.ALL)
//	private OrderItems orderItems = new OrderItems();
	
	public Medicine() {
	
		listOfPharmacy = new HashSet<PharmacyMedicines>();
		listOnPrescription = new HashSet<PrescriptionMedicines>();
	}
	
//	public ArrayList<Pharmacy> getListOfPharmacies() {
//		return listOfPharmacies;
//	}
//
//
//
//	public void setListOfPharmacies(ArrayList<Pharmacy> listOfPharmacies) {
//		this.listOfPharmacies = listOfPharmacies;
//	}

	public int getId() {
		return id;
	}
	



	/**
	 * @return the orderItems
	 */
//	public OrderItems getOrderItems() {
//		return orderItems;
//	}
//
//	/**
//	 * @param orderItems the orderItems to set
//	 */
//	public void setOrderItems(OrderItems orderItems) {
//		this.orderItems = orderItems;
//	}

	/**
	 * @return the listOfPharmacy
	 */
		public void setId(int id) {
		this.id = id;
	}
	public final Set<PharmacyMedicines> getListOfPharmacy() {
		return listOfPharmacy;
	}

	public final void setListOfPharmacy(Set<PharmacyMedicines> listOfPharmacy) {
		this.listOfPharmacy = listOfPharmacy;
	}

	public final Set<PrescriptionMedicines> getListOnPrescription() {
		return listOnPrescription;
	}

	public final void setListOnPrescription(Set<PrescriptionMedicines> listOnPrescription) {
		this.listOnPrescription = listOnPrescription;
	}

	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public String getMedicineQuantity() {
		return medicineQuantity;
	}
	public void setMedicineQuantity(String medicineQuantity) {
		this.medicineQuantity = medicineQuantity;
	}
	public String getMedicineProposition() {
		return medicineProposition;
	}
	public void setMedicineProposition(String medicineProposition) {
		this.medicineProposition = medicineProposition;
	}
}
