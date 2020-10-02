package com.example.demo.customer.service;


import com.example.demo.customer.dto.CustomerDTO;
import com.example.demo.customer.dto.ResponseSelfDTO;
import com.example.demo.customer.entity.ResponseMessage;
import lombok.NonNull;

import java.util.List;

public interface CustomerService {
    ResponseSelfDTO createCustomer(final CustomerDTO customerDto);

    ResponseSelfDTO updateCustomer(final CustomerDTO customer, final String customerId);

    CustomerDTO getCustomer(@NonNull final String customerId);

    ResponseMessage deleteCustomer(final String customerId);

    List<CustomerDTO> getCustomers();
}
