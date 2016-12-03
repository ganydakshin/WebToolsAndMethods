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
import org.springframework.web.servlet.ModelAndView;

import com.me.web.Exception.PersonException;
import com.me.web.dao.AppointmentDAO;
import com.me.web.dao.DoctorDAO;
import com.me.web.dao.MedicineDAO;
import com.me.web.dao.PharmacyDAO;
import com.me.web.pojo.Appointment;
import com.me.web.pojo.Doctor;
import com.me.web.pojo.Medicine;
import com.me.web.pojo.Person;
import com.me.web.pojo.Pharmacy;
import com.me.web.pojo.PharmacyMedicines;
import com.me.web.pojo.Prescription;
import com.me.web.pojo.PrescriptionMedicines;

@Controller
public class PharmacyController {
	
	@RequestMapping(value="/deliverMed", method = RequestMethod.GET)
	public ModelAndView doSubmitAction2(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("pharmacy")Pharmacy pharmacy1, BindingResult result) {
		ModelAndView model = new ModelAndView();
		HttpSession session = request.getSession();
		Person user = (Person) session.getAttribute("user");
		int presId= Integer.parseInt(request.getParameter("selectPres"));
		
		MedicineDAO mDAO = new MedicineDAO();
		List<PharmacyMedicines> list = new ArrayList<PharmacyMedicines>();
		list=mDAO.getPharMed(user.getPersonId());
		List<PrescriptionMedicines> listPres = new ArrayList<PrescriptionMedicines>(); 
				listPres=mDAO.getPresMed(presId);
		if(list.size() == 0 || listPres.size() == 0) {
			model.setViewName("consulted");
			return model;
		}
		int flag = 0; 
		for(PrescriptionMedicines pm1 : listPres) {
			for(PharmacyMedicines pm : list) {
				if((pm1.getMedicineId() == pm.getMedicineId()) && (pm1.getQuantity()>pm.getQuantity())) {
					flag = 1;
					break;
				}
				
			}
		}
		if(flag == 0) {
			for(PrescriptionMedicines pm1 : listPres) {
				int flag2=0;
				for(PharmacyMedicines pm : list) {
					if(pm1.getMedicineId()==pm.getMedicineId())flag2=1;
				}
				if(flag2==0){flag=1;break;}
			}
		}
		
		if(flag == 0) {
			System.out.println("**********FUCK OFF**************");
			DoctorDAO d = new DoctorDAO();
			Prescription p = null;
			try {
				p = d.getPrescription(presId);
			} catch (PersonException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int patient = p.getPatient();
			int doctor = p.getDoctor();
			try {
				d.getAppointment2(patient, doctor);
			} catch (PersonException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			model.setViewName("success");
		}
		else {
			model.setViewName("fail");
		}
		return model;
	}
	
	@RequestMapping(value="/addToStore", method = RequestMethod.GET)
	public ModelAndView doSubmitAction(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("pharmacy")Pharmacy pharmacy1, BindingResult result) {
		ModelAndView model = new ModelAndView();
		MedicineDAO medicineDao = new MedicineDAO();
		
		HttpSession session = request.getSession();
		Pharmacy pharmacy = (Pharmacy) session.getAttribute("user");
		List<Medicine> list = new ArrayList<Medicine>();
		List<PharmacyMedicines> listOfMed = new ArrayList<PharmacyMedicines>();
		try {
			list = medicineDao.getMedicine();
			listOfMed = medicineDao.getPharMed(pharmacy.getPersonId());
		} catch (PersonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(list.size() == 0) {
			model.addObject("count",0);
		}
		else {
			model.addObject("count",1);
			model.addObject("medicines", list);
			
		}
		model.addObject("pharMed",listOfMed);
		model.setViewName("PurchaseMedicines");
		return model;
	}
	
//	@RequestMapping(value="/added", method = RequestMethod.GET)
//	public ModelAndView doSubmitAction4(HttpServletRequest request, HttpServletResponse response) {
//		ModelAndView model = new ModelAndView();
//		model.setViewName("PurchaseMedicines");
//		return model;
//	}
	
	@RequestMapping(value="/added", method = RequestMethod.GET)
	public ModelAndView doSubmitAction3(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView();
		HttpSession session= request.getSession();
		Pharmacy ph = (Pharmacy)session.getAttribute("user");
		int pharmacyId = ph.getPersonId();
		
		String med = request.getParameter("m");
		
		String quantity = request.getParameter("qty");
		
		MedicineDAO mDAO = new MedicineDAO();
		List<Medicine> list = new ArrayList<Medicine>();
		try {
			list = mDAO.getMedicine();
		} catch (PersonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int medicineId=0;
		List<PharmacyMedicines> pm = mDAO.getPharMed1(med);
		if(pm.size() == 0) {
			for(Medicine m : list) {
				if(m.getMedicineName().equals(med)) {
					mDAO.insertPharmacyMedicine(Integer.parseInt(quantity),m.getId(), ph.getPersonId());
				}
				
			}
		}
		else {
			for(Medicine m : list) {
				if(m.getMedicineName().equals(med)) {
					mDAO.updatePharmacyMedicine(Integer.parseInt(quantity)+pm.get(0).getQuantity(), m.getId(),ph.getPersonId());
				}
			}
			
		}
		
		//HttpSession session = request.getSession();
		Pharmacy pharmacy = (Pharmacy) session.getAttribute("user");
		//List<Medicine> list = new ArrayList<Medicine>();
		List<PharmacyMedicines> listOfMed = new ArrayList<PharmacyMedicines>();
		try {
			list = mDAO.getMedicine();
			listOfMed = mDAO.getPharMed(pharmacy.getPersonId());
		} catch (PersonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(list.size() == 0) {
			model.addObject("count",0);
		}
		else {
			model.addObject("count",1);
			model.addObject("medicines", list);
			
		}
		model.addObject("pharMed",listOfMed);
		model.setViewName("PurchaseMedicines");
		return model;
	}
	
	@RequestMapping(value="/deliver", method = RequestMethod.GET)
	public ModelAndView doSubmitAction2() {
		
		AppointmentDAO apDAO=new AppointmentDAO();
		List<Appointment> appoints=apDAO.getAppoints();
		List<Appointment> consulted=new ArrayList<Appointment>();
		for(Appointment A:appoints){
			if(A.getStatus().equals("consulted")){
				consulted.add(A);
			}
		}
		PharmacyDAO phDAO=new PharmacyDAO();
		List<Prescription> listOfPs=phDAO.getPrescriptions();
		List<Prescription> consultedPrescriptions=new ArrayList<Prescription>();
		for(Appointment A:consulted){
			for(Prescription B:listOfPs){
				if(A.getDoctor()==B.getDoctor()&&A.getPatient()==B.getPatient())
				{
					consultedPrescriptions.add(B);
				}
			}			
		}
		ModelAndView mv=new ModelAndView("consulted","prescription",consultedPrescriptions);
		return mv;	
	}
	
	@RequestMapping(value="/homepage3", method=RequestMethod.GET)
	public ModelAndView AddPharmacy5(@ModelAttribute("doctor")Doctor doctor, @ModelAttribute("pharmacy")Pharmacy pharmacy,@ModelAttribute("medicine")Medicine medicine, BindingResult result) {
		ModelAndView model = new ModelAndView();
		model.setViewName("PharmacyWorkArea");
		return model;
	}
	
	
}
