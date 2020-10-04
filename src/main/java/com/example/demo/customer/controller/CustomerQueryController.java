package com.example.demo.customer.controller;

import com.example.demo.customer.dto.CustomerDTO;
import com.example.demo.customer.service.CustomerQueryService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
public class CustomerQueryController {

    @NonNull
    CustomerQueryService customerService;

    @GetMapping("v1/customers/{cid}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable("cid") final String customerId) {

        final CustomerDTO customerDTO = customerService.getCustomer(customerId);
        return ResponseEntity.ok(customerDTO);
    }
    @GetMapping("v1/customers")
    public ResponseEntity<List<CustomerDTO>> getAllCustomer() {

        final List<CustomerDTO> customerDTO = customerService.getAllCustomer();
        return ResponseEntity.ok(customerDTO);
    }

}
