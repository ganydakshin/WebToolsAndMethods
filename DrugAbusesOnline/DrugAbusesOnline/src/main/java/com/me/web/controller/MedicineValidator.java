package com.me.web.controller;

import com.me.web.pojo.Medicine;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;


@Component
public class MedicineValidator implements Validator {

    public boolean supports(Class aClass)
    {
        return aClass.equals(Medicine.class);
    }

    public void validate(Object obj, Errors errors)
    {
        Medicine user = (Medicine) obj;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "medicineName", "error.invalid.medicineName", "medicineName Required");
       ValidationUtils.rejectIfEmptyOrWhitespace(errors, "medicineQuantity", "error.invalid.medicineQuantity", "medicineQuantity Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "medicineProposition", "error.invalid.medicineProposition", "medicineProposition Required");
    }
}
