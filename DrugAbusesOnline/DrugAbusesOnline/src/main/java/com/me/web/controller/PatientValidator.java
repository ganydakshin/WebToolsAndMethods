package com.me.web.controller;

import com.me.web.pojo.Patient;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;

public class PatientValidator implements Validator {

    public boolean supports(Class aClass)
    {
        return aClass.equals(Patient.class);
    }

    public void validate(Object obj, Errors errors)
    {
        Patient user = (Patient) obj;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "personName", "error.invalid.personName", "Person Name Required");
       ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "Password Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "error.invalid.userName", "Username Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "weight", "error.invalid.weight", "weight Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bloodGroup", "error.invalid.bloodGroup", "bloodGroup Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bloodPressure", "error.invalid.bloodPressure", "bloodPressure Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastVisitDate", "error.invalid.lastVisitDate", "lastVisitDate Required");
           
    }
}
