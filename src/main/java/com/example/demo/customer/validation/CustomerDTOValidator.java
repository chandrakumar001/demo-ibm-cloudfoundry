package com.example.demo.customer.validation;

import com.example.demo.customer.dto.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * The To Do List Validator
 */
@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerDTOValidator implements Validator {

    @NonNull CustomerProperties customerProperties;

    @Override
    public boolean supports(Class<?> aClass) {
        return CustomerDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        validateUtil.validateNullOrEmpty(
                errors,
                customerProperties.getAttribute().getCustomerBareDTO()
        );
    }
}