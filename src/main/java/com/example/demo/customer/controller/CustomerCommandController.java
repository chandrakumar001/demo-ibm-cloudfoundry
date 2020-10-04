package com.example.demo.customer.controller;

import com.example.demo.customer.dto.CustomerDTO;
import com.example.demo.customer.dto.ResponseMessage;
import com.example.demo.customer.service.CustomerCommandService;
import com.example.demo.customer.validation.CustomerDTOValidator;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
public class CustomerCommandController {

    public static final String CUSTOMER_ID = "cid";
    public static final String V1_CUSTOMERS_CID_PATH = "v1/customers/{cid}";
    public static final String V1_CUSTOMERS = "v1/customers";

    @NonNull CustomerDTOValidator customerDTOValidator;
    @NonNull CustomerCommandService customerCommandService;


    @GetMapping
    public String getHelloWord() {
        return "Hello world";
    }

    @DeleteMapping(V1_CUSTOMERS_CID_PATH)
    public ResponseEntity<ResponseMessage> deleteCustomer(
            @PathVariable(CUSTOMER_ID) final String customerId) {

        final ResponseMessage responseMessage = customerCommandService.deleteCustomer(customerId);
        return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
    }

    @PostMapping(V1_CUSTOMERS)
    public ResponseEntity<CustomerDTO> createCustomer(
            @RequestBody final CustomerDTO customerDTO) {

        final CustomerDTO customer = customerCommandService.createCustomer(
                customerDTO
        );
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @PutMapping(V1_CUSTOMERS_CID_PATH)
    public ResponseEntity<CustomerDTO> updateCustomer(
            @RequestBody final CustomerDTO customerDTO,
            @PathVariable(CUSTOMER_ID) final String customerId) {

        final CustomerDTO customer = customerCommandService.updateCustomer(
                customerDTO,
                customerId
        );
        return new ResponseEntity<>(customer, HttpStatus.ACCEPTED);
    }

}
