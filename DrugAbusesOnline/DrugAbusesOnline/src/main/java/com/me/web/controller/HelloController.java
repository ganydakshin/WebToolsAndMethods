package com.me.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.me.web.Exception.PersonException;
import com.me.web.dao.PersonDAO;
import com.me.web.pojo.Person;

@Controller
public class HelloController {

	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView welcomePage() {
		
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Custom Login Form");
		model.addObject("message", "This is welcome page!");
		model.setViewName("hello");
		return model;
	}
	
	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Custom Login Form");
		model.addObject("message", "This is protected page!");
		model.setViewName("admin");

		return model;

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}

	@RequestMapping(value = "/workArea", method = RequestMethod.POST)
	public ModelAndView login2(HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password) {
		HttpSession session=request.getSession();
		ModelAndView model = new ModelAndView();
		PersonDAO persondao=new PersonDAO();
		Person user=null;
		try {
		
			model.addObject("error", false);
			user=persondao.getPerson(username);
			if(user != null) {
				if(user.getPassword().equals(password)){
					session.setAttribute("user", user);
					model.addObject("loggedinUser", user.getPersonName());
					model.addObject("person",user);
					if(user.getType().equals("Person")) {
                    	model.addObject("type", "Person");
                    	model.setViewName("PatientWorkArea");
                    }
                    else if(user.getType().equals("Controller")){
                    	model.addObject("type", "Controller");
                    	
                    	model.setViewName("ControllerWorkArea");
                    }
                    else if(user.getType().equals("Pharmacy")) {
                    	model.addObject("type", "Pharmacy");
                    	
                    	model.setViewName("PharmacyWorkArea");
                    }
                    else if(user.getType().equals("Doctor")) {
                    	model.addObject("type", "Doctor");
                    	
                    	model.setViewName("DoctorWorkArea");
                    }
				}
				else {
					
					model.addObject("error", "Invalid Credentials");
					model.setViewName("login");
				}
			}
			else {
				model.addObject("error", "Invalid Credentials!!!");
				model.setViewName("login");
			}
		} catch (PersonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return model;

	}

}