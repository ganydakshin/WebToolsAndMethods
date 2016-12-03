package com.me.web.controller;

import com.me.web.pojo.Doctor;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;

@Component
public class DoctorValidator implements Validator {

    public boolean supports(Class aClass)
    {
        return aClass.equals(Doctor.class);
    }

    public void validate(Object obj, Errors errors)
    {
        Doctor user = (Doctor) obj;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "personName", "error.invalid.personName", "Person Name Required");
       ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "Password Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "error.invalid.userName", "Username Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ssn", "error.invalid.ssn", "SSN Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age", "error.invalid.age", "Age Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "specilization", "error.invalid.specilization", "Specilization Required");

    }
}
