package com.me.web.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.web.Exception.PersonException;
import com.me.web.pojo.Appointment;
import com.me.web.pojo.Doctor;

public class AppointmentDAO extends DAO {

	public  List<Appointment> getAppoints(){
		try {
			begin();
			Query q=getSession().createQuery("from Appointment");
			List<Appointment> appoints=new ArrayList<Appointment>();
			appoints=q.list();
			commit();
			return appoints;
		}
		catch(HibernateException e){
			rollback();
		//	throw new PersonException("Exception while fetching user: " + e.getMessage());
		}
		finally{
			close();
		}
		return null;
	}
	public Appointment createAppointment(int doctor, int patient) throws PersonException {
		try {
			begin();
			Appointment appointment = new Appointment();
			appointment.setDoctor(doctor);
			appointment.setPatient(patient);
			appointment.setStatus("pending");
			getSession().save(appointment);
			commit();
			return appointment;
		}
		catch(HibernateException e){
			rollback();
			throw new PersonException("Exception while fetching user: " + e.getMessage());
		}
		finally{
			close();
		}
	}
}
