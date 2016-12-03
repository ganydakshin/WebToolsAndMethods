package com.me.web.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.web.Exception.PersonException;
import com.me.web.pojo.Medicine;
import com.me.web.pojo.Person;
import com.me.web.pojo.Pharmacy;
import com.me.web.pojo.Prescription;

public class PharmacyDAO extends DAO {

	public Pharmacy create(int age, String password, String personName, String type, String userName, String pharmacyName, int ssn) throws PersonException {
		try {	
			begin();
			Pharmacy pharmacy = new Pharmacy();
			pharmacy.setAge(age);
			pharmacy.setPassword(password);
			pharmacy.setPersonName(personName);
			pharmacy.setType(type);
			pharmacy.setUserName(userName);
			pharmacy.setPharmacyName(pharmacyName);
			pharmacy.setSsn(ssn);
			getSession().save(pharmacy);
			commit();
			return pharmacy;
		}
		catch(HibernateException e) {
			rollback();
	        //throw new AdException("Could not create user " + username, e);
	        throw new PersonException("Exception while creating user: " + e.getMessage());
		}
		finally{
			close();
		}
	}
	
	public List<Pharmacy> getPharmacy() throws PersonException {
		try {
			begin();
			Query q=getSession().createQuery("from Pharmacy");
			List<Pharmacy> persons=new ArrayList<Pharmacy>();
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
	
	public List<Prescription> getPrescriptions(){
		try{
			begin();
			Query q=getSession().createQuery("from Prescription");
			List<Prescription> prescriptions=new ArrayList<Prescription>();
			prescriptions=q.list();
			commit();
			return prescriptions;
		}
		catch(Exception E){
			E.printStackTrace();
			
		}
		finally{
			close();
		}
		return null;
		
	}
	
}
