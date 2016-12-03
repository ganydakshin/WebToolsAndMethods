package com.me.web.pojo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="PHARMACY")

@PrimaryKeyJoinColumn(name="personId")
public class Pharmacy extends Person {
	
	@Column(name="pharmacyName")
	private String pharmacyName;
	
	@Column(name="manager")
	private String pharmacyManager;
	
	@Column(name="ssn")
	private int ssn;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="personId")
	private Set<PharmacyMedicines> listOfMedicine=new HashSet<PharmacyMedicines>();
	
	
//	@ManyToMany(cascade=CascadeType.ALL)
//	@JoinTable(name="PHARMACY_MEDICINES", joinColumns={@JoinColumn(name="pharmacyId")}, inverseJoinColumns={@JoinColumn(name="medicineId")})
//	private ArrayList<Medicine> listOfMedicines;
//	
	public Pharmacy() {
	//	listOfMedicines = new ArrayList<Medicine>();
		listOfMedicine = new HashSet<PharmacyMedicines>();
	}
	
//	public ArrayList<Medicine> getListOfMedicines() {
//		return listOfMedicines;
//	}
//
//
//
//	public void setListOfMedicines(ArrayList<Medicine> listOfMedicines) {
//		this.listOfMedicines = listOfMedicines;
//	}



	public int getSsn() {
		return ssn;
	}
	
	public final Set<PharmacyMedicines> getListOfMedicine() {
		return listOfMedicine;
	}

	public final void setListOfMedicine(Set<PharmacyMedicines> listOfMedicine) {
		this.listOfMedicine = listOfMedicine;
	}

	public void setSsn(int ssn) {
		this.ssn = ssn;
	}
	public String getPharmacyName() {
		return pharmacyName;
	}
	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}
	public String getPharmacyManager() {
		return pharmacyManager;
	}
	public void setPharmacyManager(String pharmacyManager) {
		this.pharmacyManager = pharmacyManager;
	}
	
}
