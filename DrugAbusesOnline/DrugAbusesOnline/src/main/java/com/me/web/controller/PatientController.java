package com.me.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.me.web.Exception.PersonException;
import com.me.web.dao.AppointmentDAO;
import com.me.web.dao.DoctorDAO;
import com.me.web.dao.PersonDAO;
import com.me.web.pojo.Doctor;
import com.me.web.pojo.Medicine;
import com.me.web.pojo.Person;
import com.me.web.pojo.Pharmacy;

@Controller
public class PatientController {

	@RequestMapping(value="/appointment", method=RequestMethod.GET)
	public ModelAndView AddAppointment1(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("doctor")Doctor doctor1, BindingResult result) {
		DoctorDAO docDAO = new DoctorDAO();
		//HttpSession session = request.getSession();
		
		List<Doctor> listOfDoctors = new ArrayList<Doctor>();
		try {
			listOfDoctors=docDAO.getDoctor();
			
		} catch (PersonException e) {
			
			e.printStackTrace();
		}
		ModelAndView model = new ModelAndView();
		if(listOfDoctors.size() == 0) {
			model.addObject("count",0);
		}
		else {
			
			model.addObject("count",1);
			model.addObject("doctors", listOfDoctors);
		}
		model.setViewName("AddAppointment");
		return model;
	}
	
	
	
	@RequestMapping(value="/appointmentConfirmation", method=RequestMethod.GET)
	public ModelAndView AddAppointment2(@RequestParam(value = "doctor1", required = true) String doc,
			HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute("doctor")Doctor doctor1, BindingResult result) {
		AppointmentDAO appDao = new AppointmentDAO();
		HttpSession session = request.getSession();
		Person user = (Person)session.getAttribute("user");
		int personId=user.getPersonId();
		DoctorDAO docDao = new DoctorDAO();
		List<Doctor> listOfDoctors = null;
		try {
			listOfDoctors = docDao.getDoctor();
		} catch (PersonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int doctorId =0;
		for(Doctor d : listOfDoctors) {
			if(d.getUserName().equals(doc)) {
				doctorId = d.getPersonId();
			}
		}
		try {
			appDao.createAppointment(doctorId, personId);
		} catch (PersonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView model = new ModelAndView();
		model.setViewName("AppointmentSuccess");
		return model;
			
		
	}
	
	@RequestMapping(value="/homepage1", method=RequestMethod.GET)
	public ModelAndView AddPharmacy5(@ModelAttribute("doctor")Doctor doctor, @ModelAttribute("pharmacy")Pharmacy pharmacy,@ModelAttribute("medicine")Medicine medicine, BindingResult result) {
		ModelAndView model = new ModelAndView();
		model.setViewName("PatientWorkArea");
		return model;
	}
	
	@RequestMapping(value="/ajax", method=RequestMethod.GET)
	public void ajax(HttpServletRequest request, HttpServletResponse response) {
		
		
		String s1=request.getParameter("q");
		PersonDAO p1 = new PersonDAO();
		Person p=null;
		try {
			p = p1.getPerson(s1);
		} catch (PersonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject obj=null;
		if(p != null){
			obj=new JSONObject();
			try {
				obj.put("msg", "Username unavailable");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//s="Username Available";
		}
		else {
			obj=new JSONObject();
			try {
				obj.put("msg", "Username available");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//s="Username unavailable";
		}
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println(obj);
		
	}
}
