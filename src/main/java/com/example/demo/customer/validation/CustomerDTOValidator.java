package com.example.demo.customer.validation;

import com.example.demo.customer.dto.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Map;

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

        validateNullOrEmpty(errors, customerProperties.getAttribute().getCustomerBareDTO());
    }

    public static void validateNullOrEmpty(final Errors errors,
                                           final Map<String, String> map) {
        if (CollectionUtils.isEmpty(map)) {
            return;
        }
        map.forEach((key, value) -> ValidationUtils.rejectIfEmptyOrWhitespace(
                errors, key, value
        ));
    }
}