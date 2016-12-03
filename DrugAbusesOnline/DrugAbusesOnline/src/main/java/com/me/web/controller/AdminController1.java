package com.me.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.me.web.dao.MedicineDAO;
import com.me.web.dao.PersonDAO;
import com.me.web.dao.PharmacyDAO;
import com.me.web.pojo.Person;
import com.me.web.pojo.Pharmacy;


@Controller
@RequestMapping("/addPharmacy")
public class AdminController1 {
	
//	@Autowired
//	@Qualifier("pharmacyValidator")
//	PharmacyValidator pharmacyValidator;
//	
//	@InitBinder
//	private void initBinder(WebDataBinder binder)
//	{
//		binder.setValidator(pharmacyValidator);
//	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView AddPharmacy1(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("pharmacy")Pharmacy pharmacy1, BindingResult result) 
		{	
		
//		pharmacyValidator.validate(pharmacy1, result);
//		if(result.hasErrors())
//		{
//			ModelAndView mv = new ModelAndView();
//			mv.setViewName("addPharmacy");
//			return mv;
//		}
//		
		
		PharmacyDAO phardao=new PharmacyDAO();
		PersonDAO personDao = new PersonDAO();
		//Person p=null;
//		try {
//			p = personDao.getPerson(pharmacy1.getUserName());
//		} catch (PersonException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		//	if(p==null) {
			try {
				phardao.create(pharmacy1.getAge(), pharmacy1.getPassword(), pharmacy1.getPersonName(), "Pharmacy", pharmacy1.getUserName(), pharmacy1.getPharmacyName(), pharmacy1.getSsn());
			} catch (PersonException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List<Pharmacy> listOfPharmacies = new ArrayList<Pharmacy>();
			try {
				listOfPharmacies=phardao.getPharmacy();
			} catch (PersonException e) {
				
				e.printStackTrace();
			}
			ModelAndView model = new ModelAndView();
			if(listOfPharmacies.size() == 0) {
				model.addObject("count",0);
			}
			else {
				model.addObject("count",1);
				System.out.println("GANESH IS HERE");
				model.addObject("pharmacies", listOfPharmacies);
			
			}
			model.addObject("error1", 0);
			model.setViewName("addPharmacy");
			return model;
//			}
//			else {
//				ModelAndView model = new ModelAndView();
//				model.addObject("error1", 1);
//				model.setViewName("addPharmacy");
//				return model;
//			}
		}
	
	
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView AddPharmacy(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("pharmacy")Pharmacy pharmacy1, BindingResult result) {
		PharmacyDAO pharmacy = new PharmacyDAO();
		//HttpSession session = request.getSession();
		
		List<Pharmacy> listOfPharmacies = new ArrayList<Pharmacy>();
		try {
			listOfPharmacies=pharmacy.getPharmacy();
		} catch (PersonException e) {
			
			e.printStackTrace();
		}
		ModelAndView model = new ModelAndView();
		if(listOfPharmacies.size() == 0) {
			model.addObject("count",0);
		}
		else {
			model.addObject("count",1);
			model.addObject("pharmacies", listOfPharmacies);
		
		
		}
		
		model.setViewName("addPharmacy");
		return model;
	}
	
	
	
}
