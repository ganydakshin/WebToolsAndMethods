package com.me.web.controller;

import com.me.web.pojo.Medicine;
import com.me.web.pojo.Pharmacy;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;

@Component
public class PharmacyValidator implements Validator {

    public boolean supports(Class aClass)
    {
        return aClass.equals(Pharmacy.class);
    }

    public void validate(Object obj, Errors errors)
    {
    	Pharmacy user = (Pharmacy) obj;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pharmacyName", "error.invalid.pharmacyName", "pharmacyName Required");
       ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pharmacyManager", "error.invalid.pharmacyManager", "pharmacyManager Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ssn", "error.invalid.ssn", "ssn Required");
    }
}
