package com.example.demo.customer.controller;

import com.example.demo.customer.dto.CustomerDTO;
import com.example.demo.customer.entity.ResponseMessage;
import com.example.demo.customer.service.CustomerServiceGen;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
public class CustomerController {

    @NonNull
    CustomerServiceGen customerServiceGen;


    @GetMapping
    public String getHelloWord() {
        return "Hello world";
    }

    @GetMapping("v1/customers/{cid}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable("cid") final String customerId) {
        final CustomerDTO customerDTO = customerServiceGen.getCustomer(customerId);
        return ResponseEntity.ok(customerDTO);
    }

    @PostMapping("v1/customers")
    public ResponseEntity<ResponseMessage> createCustomer(
            @RequestBody final CustomerDTO customerDTO) {

        final ResponseMessage responseMessage = customerServiceGen.createCustomer(customerDTO);
        return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
    }

}
