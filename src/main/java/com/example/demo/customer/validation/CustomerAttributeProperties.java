package com.example.demo.customer.validation;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class CustomerAttributeProperties {
    private Map<String, String> customerBareDTO = new HashMap<>();
}
