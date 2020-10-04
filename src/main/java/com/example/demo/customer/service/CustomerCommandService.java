package com.example.demo.customer.service;


import com.example.demo.customer.dto.CustomerDTO;
import com.example.demo.customer.dto.ResponseMessage;

public interface CustomerCommandService {

    CustomerDTO createCustomer(final CustomerDTO customerDto);

    CustomerDTO updateCustomer(final CustomerDTO customer, final String customerId);

    ResponseMessage deleteCustomer(final String customerId);
}
