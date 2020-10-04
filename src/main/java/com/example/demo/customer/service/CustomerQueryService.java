package com.example.demo.customer.service;


import com.example.demo.customer.dto.CustomerDTO;
import lombok.NonNull;

import java.util.List;

public interface CustomerQueryService {

    CustomerDTO getCustomer(@NonNull final String customerId);

    List<CustomerDTO> getAllCustomer();
}
