package com.example.demo.customer.resource;

import com.example.demo.customer.dto.CustomerDTO;
import com.example.demo.customer.dto.ResponseMessage;
import com.example.demo.customer.service.CustomerCommandService;
import com.example.demo.customer.validation.CustomerDTOValidator;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Tag(name = "customer")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerCommandController {


    @NonNull CustomerDTOValidator customerDTOValidator;
    @NonNull CustomerCommandService customerCommandService;


    @GetMapping
    public String getHelloWord() {
        return "Hello world";
    }

    @DeleteMapping(CustomerConstant.V1_CUSTOMERS_CID_PATH)
    public ResponseEntity<ResponseMessage> deleteCustomer(
            @PathVariable(CustomerConstant.CUSTOMER_ID) final String customerId) {

        final ResponseMessage responseMessage = customerCommandService.deleteCustomer(customerId);
        return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
    }

    @PostMapping(CustomerConstant.V1_CUSTOMERS)
    public ResponseEntity<CustomerDTO> createCustomer(
            @RequestBody @Valid final CustomerDTO customerDTO) {

        final CustomerDTO customer = customerCommandService.createCustomer(
                customerDTO
        );
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @PutMapping(CustomerConstant.V1_CUSTOMERS_CID_PATH)
    public ResponseEntity<CustomerDTO> updateCustomer(
            @RequestBody @Valid final CustomerDTO customerDTO,
            @PathVariable(CustomerConstant.CUSTOMER_ID) final String customerId) {

        final CustomerDTO customer = customerCommandService.updateCustomer(
                customerDTO,
                customerId
        );
        return new ResponseEntity<>(customer, HttpStatus.ACCEPTED);
    }

    /**
     * Validate the request body object(customerDTO)
     *
     * @param dataBinder as DataBinder
     */
    @InitBinder
    protected void setupBinder(final DataBinder dataBinder) {
        dataBinder.addValidators(customerDTOValidator);
    }

}
