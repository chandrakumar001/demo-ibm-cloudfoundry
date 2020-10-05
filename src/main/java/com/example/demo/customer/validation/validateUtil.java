package com.example.demo.customer.validation;

import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import java.util.Map;

public class validateUtil {

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
