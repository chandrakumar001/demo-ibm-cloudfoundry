package com.example.demo.customer.validation;

import com.example.demo.customer.dto.CustomerDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * The To Do List Validator
 */
public class CustomerDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return CustomerDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "uniqueId", "customerDTO.uniqueId",
                "Property 'uniqueId' must not be empty.");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "customerDTO.name",
                "Property 'name' must not be empty.");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "customerDTO.address",
                "Property 'address' must not be empty.");
    }
}