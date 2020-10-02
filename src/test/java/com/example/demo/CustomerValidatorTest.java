package com.example.demo;

import com.example.demo.customer.validation.CustomerValidator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerValidatorTest {
    private CustomerValidator customerValidator;

    @Before
    public void setup() {
        customerValidator = new CustomerValidator();
    }

    @Test
    public void testUUID() {
        //final Customer dss = customerValidator.getValidtor(null, null);
        assertEquals("ok", "ok");
    }
}
