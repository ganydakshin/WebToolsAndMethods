package com.me.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.me.web.Exception.PersonException;
import com.me.web.dao.PersonDAO;
import com.me.web.pojo.Person;



@Controller
@RequestMapping("/signUp.htm")
public class PersonController {
	
	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView doSubmitAction(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("patient")Person person, BindingResult result) throws Exception {
		//personValidator.validate(person, result);
		PersonDAO personDao = new PersonDAO();
		int flag=0;
		ModelAndView model = new ModelAndView();
		Person p = personDao.getPerson(person.getUserName());
		if(p != null) {
			flag = 1;
			//session.setAttribute("error1", flag);
			model.addObject("error1", flag);
			model.setViewName("signUp");
			return model;
		}
		String type="Person";
		
		try {
			
			personDao.create(person.getPersonName(), person.getAge(), person.getUserName(), person.getPassword(), person.getEmail(),type);
			flag=0;
//			Mail mail = new Mail();
//			mail.sendMail(person.getEmail(), person.getPersonName());
//			//session.setAttribute("error1", flag);
			model.addObject("error1",flag);
		}
		catch(PersonException e) {
			System.out.println("Exception: " + e.getMessage());
		}
		model.setViewName("addedUser");
		return model;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView initializeForm(@ModelAttribute("patient")Person person, BindingResult result) {
		ModelAndView model = new ModelAndView();
		model.setViewName("signUp");
		return model;
	}
}
