package com.me.web.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.me.web.dao.DoctorDAO;
import com.me.web.dao.MedicineDAO;
import com.me.web.dao.PersonDAO;
import com.me.web.pojo.Doctor;
import com.me.web.pojo.Medicine;
import com.me.web.pojo.Patient;
import com.me.web.pojo.Person;
import com.me.web.pojo.Pharmacy;
import com.me.web.pojo.Prescription;
import com.me.web.pojo.PrescriptionMedicines;
@Controller
public class DoctorController {

	@RequestMapping(value="/writePrescription", method=RequestMethod.GET)
	public ModelAndView writePrescription(HttpServletRequest request, HttpServletResponse response,@RequestParam(value="patient1")String patient) {
		
		ModelAndView model = new ModelAndView();
		model.addObject("patient",patient);
		MedicineDAO medicineDao = new MedicineDAO();
		List<Medicine> listOfMedicines = new ArrayList<Medicine>();
		try {
			System.out.println(listOfMedicines.size());
			listOfMedicines = medicineDao.getMedicine();
		} catch (PersonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addObject("medicine", listOfMedicines);
		model.setViewName("WritePrescription");
		
		return model;
	}
	
	@RequestMapping(value="/viewPatient", method=RequestMethod.GET)
	public ModelAndView AddPharmacy5(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("doctor")Doctor doctor, @ModelAttribute("pharmacy")Pharmacy pharmacy,@ModelAttribute("medicine")Medicine medicine, BindingResult result) {
		ModelAndView model = new ModelAndView();
		HttpSession session = request.getSession();
		
		Person user = (Person)session.getAttribute("user");
		int personId = user.getPersonId();
		
		DoctorDAO docDao = new DoctorDAO();
		List<Patient> listPatient=new ArrayList<Patient>();
		try {
			listPatient = docDao.getPatient(personId);
		} catch (PersonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(listPatient.size() == 0) {
			model.addObject("count",0);
		}
		else {
			model.addObject("count",1);
			model.addObject("patients",listPatient);
		}
		model.setViewName("ViewPatient");
		return model;
	}
	
	@RequestMapping(value="/homepage2", method=RequestMethod.GET)
	public ModelAndView AddPharmacy6(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("doctor")Doctor doctor, @ModelAttribute("pharmacy")Pharmacy pharmacy,@ModelAttribute("medicine")Medicine medicine, BindingResult result) {
		ModelAndView model = new ModelAndView();
		model.setViewName("DoctorWorkArea");
		return model;
	}
	
	@RequestMapping(value="/pres", method=RequestMethod.GET)
	public ModelAndView AddPharmacy7(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("doctor")Doctor doctor, @ModelAttribute("pharmacy")Pharmacy pharmacy,@ModelAttribute("medicine")Medicine medicine, BindingResult result) {
		String[] Listmedicines = request.getParameterValues("medicines");
		String[] medquantities = request.getParameterValues("medQuantity");
		//System.out.println(Listmedicines[2]);
		//System.out.println(medquantities[2]);
		HttpSession session=request.getSession();
		Prescription P=new Prescription();
		Person D=(Person) session.getAttribute("user");
		P.setDoctor(D.getPersonId());
		
		PersonDAO persondao=new PersonDAO();
		int id=0;
		try {
			System.out.println(request.getParameter("patient1000"));
			id=persondao.getAllPerson(request.getParameter("patient1000"));
		} catch (PersonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		P.setPatient(id);
		
		MedicineDAO mdao = new MedicineDAO();
		List<Medicine> list = null;
		try {
			list = mdao.getMedicine();
		} catch (PersonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int[] mId=new int[Listmedicines.length];
		for(int i=0;i<Listmedicines.length;i++) {
			for(Medicine m : list) {
			
				if(m.getMedicineName().equals(Listmedicines[i])) {
					mId[i]=m.getId();
				}
			}
			
		}
		
		for(int i=0;i<Listmedicines.length;i++) {
			PrescriptionMedicines pm = new PrescriptionMedicines();
			pm.setMedicineId(mId[i]);
			pm.setQuantity(Integer.parseInt(medquantities[i]));
			P.getMedicineList().add(pm);
		}
		
		DoctorDAO dDao = new DoctorDAO();
		dDao.create(P);
		
		try {
			dDao.getAppointment(id, D.getPersonId());
		} catch (PersonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView mv=new ModelAndView();
		mv.setViewName("PrescriptionProvided");
		return mv;
	}
	
	@RequestMapping(value="/pres", method=RequestMethod.POST)
	public ModelAndView AddPharmacy8(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("doctor")Doctor doctor, @ModelAttribute("pharmacy")Pharmacy pharmacy,@ModelAttribute("medicine")Medicine medicine, BindingResult result) {
		String[] Listmedicines = request.getParameterValues("medicines");
		String[] medquantities = request.getParameterValues("medQuantity");
		
		System.out.println(Listmedicines[2]);
		System.out.println(medquantities[2]);
		ModelAndView mv=new ModelAndView();
		mv.setViewName("PrescriptionProvided");
		return mv;
	}
	
}
