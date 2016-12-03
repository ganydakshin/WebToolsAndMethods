package com.me.web.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.web.Exception.PersonException;
import com.me.web.pojo.Doctor;
import com.me.web.pojo.Patient;
import com.me.web.pojo.Pharmacy;
import com.me.web.pojo.Prescription;
import com.me.web.pojo.PrescriptionMedicines;

public class DoctorDAO extends DAO {

	public Doctor create(int age, String password, String personName, String type, String userName, String specilization, int ssn) throws PersonException {
		try {	
			begin();
			Doctor pharmacy = new Doctor();
			pharmacy.setAge(age);
			pharmacy.setPassword(password);
			pharmacy.setPersonName(personName);
			pharmacy.setType(type);
			pharmacy.setUserName(userName);
			pharmacy.setSpecilization(specilization);
			pharmacy.setSsn(ssn);
			getSession().save(pharmacy);
			commit();
			return pharmacy;
		}
		catch(HibernateException e) {
			rollback();
	        
	        throw new PersonException("Exception while creating user: " + e.getMessage());
		}
		finally{
			close();
		}
	}
	
	public List<Doctor> getDoctor() throws PersonException {
		try {
			begin();
			Query q=getSession().createQuery("from Doctor");
			List<Doctor> persons=new ArrayList<Doctor>();
			persons=q.list();
			commit();
			return persons;
		}
		catch(HibernateException e){
			rollback();
			throw new PersonException("Exception while fetching user: " + e.getMessage());
		}
		finally{
			close();
		}
	}

	public Prescription create(Prescription p) {
		try {	
			begin();
			//Prescription pharmacy = new Doctor();
			getSession().save(p);
			for(PrescriptionMedicines pm : p.getMedicineList()) {
				pm.setPrescription(p.getId());
				getSession().save(pm);
			}
			commit();
			return p;
		}
		catch(HibernateException e) {
			rollback();
	        
	        //throw new PersonException("Exception while creating user: " + e.getMessage());
		}
		finally{
			close();
		}
		return null;
	}
	
	public int getAppointment(int S, int D) throws PersonException {
		try {
			begin();
			Query q=getSession().createQuery("update Appointment set status = 'consulted' where doctor = :D and patient = :S and status = 'pending'");
			q.setInteger("S", S);
			q.setInteger("D", D);
			//List<Patient> persons=new ArrayList<Patient>();
			int result = q.executeUpdate();
			commit();
			return result;
		}
		catch(HibernateException e){
			rollback();
			throw new PersonException("Exception while fetching user: " + e.getMessage());
		}
		finally{
			close();
		}
	}
	
	public int getAppointment2(int S, int D) throws PersonException {
		try {
			begin();
			Query q=getSession().createQuery("update Appointment set status = 'dispatched' where doctor = :D and patient = :S and status = 'consulted'");
			q.setInteger("S", S);
			q.setInteger("D", D);
			//List<Patient> persons=new ArrayList<Patient>();
			int result = q.executeUpdate();
			commit();
			return result;
		}
		catch(HibernateException e){
			rollback();
			throw new PersonException("Exception while fetching user: " + e.getMessage());
		}
		finally{
			close();
		}
	}
	
	public List<Patient> getPatient(int S) throws PersonException {
		try {
			begin();
			Query q=getSession().createQuery("from Patient where personId in (select patient from Appointment where doctor = :S and status = :D)");
			q.setInteger("S", S);
			q.setString("D", "pending");
			List<Patient> persons=new ArrayList<Patient>();
			persons=q.list();
			commit();
			return persons;
		}
		catch(HibernateException e){
			rollback();
			throw new PersonException("Exception while fetching user: " + e.getMessage());
		}
		finally{
			close();
		}
	}
	
	public Prescription getPrescription(int p) throws PersonException {
		try {
			begin();
			Query q=getSession().createQuery("from Prescription where id = :p");
			q.setInteger("p", p);
			
			Prescription p1 = (Prescription)q.uniqueResult();
			commit();
			return p1;
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
