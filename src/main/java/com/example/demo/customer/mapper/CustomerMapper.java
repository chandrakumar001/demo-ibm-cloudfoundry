package com.example.demo.customer.mapper;

import com.example.demo.customer.dto.CustomerDTO;
import com.example.demo.customer.entity.Customer;
import com.example.demo.customer.entity.CustomerUniqueIdentity;

public class CustomerMapper {

    public static Customer mapToCustomer(final CustomerDTO customerDTO) {

        final CustomerUniqueIdentity customerUniqueIdentity = new CustomerUniqueIdentity();
        customerUniqueIdentity.setUniqueId(customerDTO.getUniqueId());

        final Customer customer = new Customer();
        customer.setAddress(customerDTO.getAddress());
        customer.setUniqueId(customerUniqueIdentity);
        customer.setName(customerDTO.getName());
        return customer;
    }

    public static CustomerDTO mapToCustomerDTO(final Customer customerDB) {

        final CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName(customerDB.getName());
        customerDTO.setUniqueId(customerDB.getUniqueId().getUniqueId());
        customerDTO.setAddress(customerDB.getAddress());
        return customerDTO;
    }
}
