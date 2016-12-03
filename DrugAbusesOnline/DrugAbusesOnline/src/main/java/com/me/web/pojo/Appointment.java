package com.me.web.pojo;

import javax.persistence.*;

@Entity
@Table(name="Appointment")
public class Appointment {

	@Id
	@GeneratedValue
	@Column(name="AppointmentId", nullable=false, unique=true)
	private int appointmentId;
	
	public int getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}
	private int patient;
	private int doctor;
	
	@Column(name="status")
	private String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getPatient() {
		return patient;
	}
	public void setPatient(int patient) {
		this.patient = patient;
	}
	public int getDoctor() {
		return doctor;
	}
	public void setDoctor(int doctor) {
		this.doctor = doctor;
	}
	
}
