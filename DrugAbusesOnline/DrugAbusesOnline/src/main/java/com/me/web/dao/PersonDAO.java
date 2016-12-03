package com.me.web.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.web.pojo.Patient;
import com.me.web.pojo.Person;
import com.me.web.Exception.PersonException;


public class PersonDAO extends DAO {

	public int getAllPerson(String userName)throws PersonException{
		try{
			begin();
			Query q=getSession().createQuery("from Person");
			List<Person> persons=new ArrayList<Person>();
			persons=q.list();
			commit();
			for(Person A:persons){
				if(userName.equals(A.getUserName())){
					return A.getPersonId();
				}	
			}	
		}
		catch(HibernateException e){
			rollback();
			throw new PersonException("Exception while fetching user: " + e.getMessage());
		}
		finally{
			close();
		}
		return -1;
		}

	
	public Person getPerson(String userName)throws PersonException{
		try{
			begin();
			Query q=getSession().createQuery("from Person");
			List<Person> persons=new ArrayList<Person>();
			persons=q.list();
			commit();
			for(Person A:persons){
				if(userName.equals(A.getUserName())){
					return A;
				}	
			}	
		}
		catch(HibernateException e){
			rollback();
			throw new PersonException("Exception while fetching user: " + e.getMessage());
		}
		finally{
			close();
		}
		return null;
		}

	public Person create(String name, int age, String userName, String password, String email, String type) throws PersonException {
		try {
			begin();
			
//			Person person1 = new Person();
//			person1.setPersonName("Gany");
//			person1.setAge(25);
//			person1.setUserName("localboy99");
//			person1.setType("Controller");
//			person1.setPassword("Gany@1990");
//			getSession().save(person1);
			Patient person = new Patient();
			person.setPersonName(name);
			person.setAge(age);
			person.setUserName(userName);
			person.setType(type);
			person.setEmail(email);
			person.setPassword(String.valueOf(password));
			getSession().save(person);
			
			commit();
			
			return person;
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
}
