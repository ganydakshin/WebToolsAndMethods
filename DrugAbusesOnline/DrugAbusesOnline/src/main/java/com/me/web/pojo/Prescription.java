package com.me.web.pojo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="PRESCRIPTION")
public class Prescription {

	@Id
	@GeneratedValue
	@Column(name="prescriptionId",unique=true, nullable=false)
	private int id;
	
	private int patient;
	
	private int doctor;
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="prescription")
	private Set<PrescriptionMedicines> medicineList = new HashSet<PrescriptionMedicines>();
	public Prescription() {
		medicineList = new HashSet<PrescriptionMedicines>();
	}
	
	public int getDoctor() {
		return doctor;
	}

	public void setDoctor(int doctor) {
		this.doctor = doctor;
	}




	/**
	 * @return the patient
	 */
	



	public int getId() {
		return id;
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




	public void setId(int id) {
		this.id = id;
	}




	/**
	 * @return the medicineList
	 */
	public Set<PrescriptionMedicines> getMedicineList() {
		return medicineList;
	}




	/**
	 * @param medicineList the medicineList to set
	 */
	public void setMedicineList(Set<PrescriptionMedicines> medicineList) {
		this.medicineList = medicineList;
	}




		
}
