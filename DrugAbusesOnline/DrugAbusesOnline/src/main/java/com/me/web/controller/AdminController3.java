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
import com.me.web.dao.MedicineDAO;
import com.me.web.pojo.Doctor;
import com.me.web.pojo.Medicine;
import com.me.web.pojo.Pharmacy;

@Controller
@RequestMapping("/addMedicine")
public class AdminController3 {


//		@Autowired
//		@Qualifier("medicineValidator")
//		MedicineValidator medicineValidator;
//		
//		@InitBinder
//		private void initBinder(WebDataBinder binder)
//		{
//			binder.setValidator(medicineValidator);
//		}
//	
	
	

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView AddPharmacy3(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("medicine")Medicine doctor1, BindingResult result) 
		{
//		medicineValidator.validate(doctor1, result);
//		if(result.hasErrors())
//		{
//			ModelAndView mv = new ModelAndView();
//			mv.setViewName("addMedicine");
//			return mv;
//		}
//		
		
		
		
		
		MedicineDAO phardao=new MedicineDAO();
			//System.out.println(pharmacy1.getPassword()+pharmacy1.getPersonName());
			
			try {
				phardao.create(doctor1.getMedicineName(), doctor1.getMedicineQuantity(), doctor1.getMedicineProposition());
			} catch (PersonException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List<Medicine> listOfDoctors = new ArrayList<Medicine>();
			try {
				listOfDoctors=phardao.getMedicine();
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
			
			model.setViewName("addMedicine");
			return model;
		}
	
	
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView AddPharmacy4(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("medicine")Medicine doctor1, BindingResult result) {
		MedicineDAO docDAO = new MedicineDAO();
		//HttpSession session = request.getSession();
		
		List<Medicine> listOfDoctors = new ArrayList<Medicine>();
		try {
			listOfDoctors=docDAO.getMedicine();
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
		model.setViewName("addMedicine");
		return model;
	}
	
	
	
	
}
