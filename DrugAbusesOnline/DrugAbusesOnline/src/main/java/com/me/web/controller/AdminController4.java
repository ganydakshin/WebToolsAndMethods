package com.me.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.me.web.pojo.Doctor;
import com.me.web.pojo.Medicine;
import com.me.web.pojo.Pharmacy;
@Controller
public class AdminController4 {

	@RequestMapping(value="/homepage", method=RequestMethod.GET)
	public ModelAndView AddPharmacy5(@ModelAttribute("doctor")Doctor doctor, @ModelAttribute("pharmacy")Pharmacy pharmacy,@ModelAttribute("medicine")Medicine medicine, BindingResult result) {
		ModelAndView model = new ModelAndView();
		model.setViewName("ControllerWorkArea");
		return model;
	}
}
