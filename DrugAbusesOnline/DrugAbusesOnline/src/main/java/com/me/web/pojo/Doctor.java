package com.me.web.pojo;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="DOCTOR")
@PrimaryKeyJoinColumn(name="personId")

public class Doctor extends Person {
	
	@Column(name="ssn")
	private int ssn;
	
	@Column(name="qualification")
	private String qualifications;
	
	@Column(name="specilization")
	private String specilization;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="doctor")
	private Set<Prescription> listOfPrescriptions = new HashSet<Prescription>();
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="doctor")
	private Set<Appointment> listOfAppointments = new HashSet<Appointment>();
	
	public int getSsn() {
		return ssn;
	}
	public void setSsn(int ssn) {
		this.ssn = ssn;
	}
	public String getQualifications() {
		return qualifications;
	}
	public Set<Prescription> getListOfPrescriptions() {
		return listOfPrescriptions;
	}
	public void setListOfPrescriptions(Set<Prescription> listOfPrescriptions) {
		this.listOfPrescriptions = listOfPrescriptions;
	}
	public Set<Appointment> getListOfAppointments() {
		return listOfAppointments;
	}
	public void setListOfAppointments(Set<Appointment> listOfAppointments) {
		this.listOfAppointments = listOfAppointments;
	}
	public void setQualifications(String qualifications) {
		this.qualifications = qualifications;
	}
	public String getSpecilization() {
		return specilization;
	}
	public void setSpecilization(String specilization) {
		this.specilization = specilization;
	}
}
