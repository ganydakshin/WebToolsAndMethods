package com.me.web.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.web.Exception.PersonException;
import com.me.web.pojo.Doctor;
import com.me.web.pojo.Medicine;
import com.me.web.pojo.PharmacyMedicines;
import com.me.web.pojo.PrescriptionMedicines;

public class MedicineDAO extends DAO {

	public List<PrescriptionMedicines> getPresMed(int S) {
		try {
			begin();
			Query q = getSession().createQuery("from PrescriptionMedicines where prescription =:S");
			q.setInteger("S", S);
			List<PrescriptionMedicines> listPres = q.list();
			commit();
			return listPres;
		}
		catch(HibernateException e){
			rollback();
			try {
				throw new PersonException("Exception while fetching user: " + e.getMessage());
			} catch (PersonException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		finally{
			close();
		}
		return null;
		
	}
	
	public Medicine create(String medicineName, String quantity, String proposition) throws PersonException {
	
	try {	
		begin();
		Medicine medicine = new Medicine();
		medicine.setMedicineName(medicineName);
		medicine.setMedicineQuantity(quantity);
		medicine.setMedicineProposition(proposition);
		getSession().save(medicine);
		commit();
		return medicine;
	}
	catch(HibernateException e) {
		rollback();
        //throw new AdException("Could not create user " + username, e);
        throw new PersonException("Exception while creating user: " + e.getMessage());
	}
	}
	
	public List<Medicine> getMedicine() throws PersonException {
		try {
			begin();
			Query q=getSession().createQuery("from Medicine");
			List<Medicine> persons=new ArrayList<Medicine>();
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
	
	public List<PharmacyMedicines> getPharMed(int s) {
		try {
			begin();
			Query q=getSession().createQuery("from PharmacyMedicines where personId = :s");
			q.setInteger("s", s);
			List<PharmacyMedicines> list = new ArrayList<PharmacyMedicines>();
			list=q.list();
			
			commit();
			return list;
		}
		catch(HibernateException e){
			rollback();
			return null;
			//throw new PersonException("Exception while fetching user: " + e.getMessage());
		}
		finally{
			close();
		}
	}
	
	public List<PharmacyMedicines> getPharMed1(String s) {
		try {
			begin();
			
			Query q=getSession().createQuery("from PharmacyMedicines where medicineId = (select id from Medicine where medicineName =:s)");
			q.setString("s", s);
			//List<PharmacyMedicines> list = new ArrayList<PharmacyMedicines>();
			List<PharmacyMedicines> pm = q.list();
			//System.out.println("FUCK YOU*********************************"+pm.getMedicineId());
			commit();
			return pm;
		}
		catch(HibernateException e){
			rollback();
			return null;
			//throw new PersonException("Exception while fetching user: " + e.getMessage());
		}
		finally{
			close();
		}
	}
	
	public int updatePharmacyMedicine(int s, int d, int f) {
		try {
			begin();
			Query q=getSession().createQuery("update PharmacyMedicines set quantity = :s where medicineId = :d and personId = :f");
			q.setInteger("s", s);
			q.setInteger("d", d);
			q.setInteger("f", f);
			//List<PharmacyMedicines> list = new ArrayList<PharmacyMedicines>();
			int result = q.executeUpdate();
			
			commit();
			return result;
		}
		catch(HibernateException e){
			rollback();
			return -1;
			//throw new PersonException("Exception while fetching user: " + e.getMessage());
		}
		finally{
			close();
		}
	}
	
	public PharmacyMedicines insertPharmacyMedicine(int quantity, int medicineId, int pharmacyId) {
		try {
			begin();
			PharmacyMedicines pm = new PharmacyMedicines();
			pm.setMedicineId(medicineId);
			pm.setPersonId(pharmacyId);
			pm.setQuantity(quantity);
			getSession().save(pm);
			commit();
			return pm;
		}
		catch(HibernateException e){
			rollback();
			return null;
			//throw new PersonException("Exception while fetching user: " + e.getMessage());
		}
		finally{
			close();
		}
	}
}
