package com.me.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.me.web.Exception.PersonException;
import com.me.web.dao.DoctorDAO;
import com.me.web.dao.PersonDAO;
import com.me.web.pojo.Doctor;
import com.me.web.pojo.Person;


@Controller
@RequestMapping("/addDoctor")
public class AdminController2 {
	
	
//		@Autowired
//		@Qualifier("doctorValidator")
//		DoctorValidator doctorValidator;
//		
//		@InitBinder
//		private void initBinder(WebDataBinder binder)
//		{
//			binder.setValidator(doctorValidator);
//		}

		@RequestMapping(method = RequestMethod.POST)
	public ModelAndView AddPharmacy2(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("doctor")Doctor doctor1, BindingResult result) 
		{	
		
//		doctorValidator.validate(doctor1, result);
//		if(result.hasErrors())
//		{
//			ModelAndView mv = new ModelAndView();
//			mv.setViewName("addDoctor");
//			return mv;
//		}
		
		DoctorDAO phardao=new DoctorDAO();
		PersonDAO personDao = new PersonDAO();
		Person p=null;
		try {
			p = personDao.getPerson(doctor1.getUserName());
		} catch (PersonException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			if(p != null) {
				ModelAndView model = new ModelAndView();
				model.addObject("error1", 1);
				model.setViewName("addPharmacy");
				return model;
			}
			else {
			try {
				phardao.create(doctor1.getAge(), doctor1.getPassword(), doctor1.getPersonName(), "Doctor", doctor1.getUserName(), doctor1.getSpecilization(), doctor1.getSsn());
			} catch (PersonException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List<Doctor> listOfDoctors = new ArrayList<Doctor>();
			try {
				listOfDoctors=phardao.getDoctor();
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
			model.addObject("error1", 0);
			model.setViewName("addDoctor");
			return model;
			}
			
		}
	
	
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView AddPharmacy3(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("doctor")Doctor doctor1, BindingResult result) {
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
		model.setViewName("addDoctor");
		return model;
	}
	
}
