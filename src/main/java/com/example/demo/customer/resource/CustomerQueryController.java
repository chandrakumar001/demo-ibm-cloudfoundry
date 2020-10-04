package com.example.demo.customer.resource;

import com.example.demo.customer.dto.CustomerDTO;
import com.example.demo.customer.service.CustomerQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "customer")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerQueryController {

    @NonNull
    CustomerQueryService customerService;

    @GetMapping(CustomerConstant.V1_CUSTOMERS_CID_PATH)
    public ResponseEntity<CustomerDTO> getCustomerById(
            @PathVariable(CustomerConstant.CUSTOMER_ID) final String customerId) {

        final CustomerDTO customerDTO = customerService.getCustomer(customerId);
        return ResponseEntity.ok(customerDTO);
    }

    @GetMapping(CustomerConstant.V1_CUSTOMERS)
    public ResponseEntity<List<CustomerDTO>> getAllCustomer() {

        final List<CustomerDTO> customerDTO = customerService.getAllCustomer();
        return ResponseEntity.ok(customerDTO);
    }

}
