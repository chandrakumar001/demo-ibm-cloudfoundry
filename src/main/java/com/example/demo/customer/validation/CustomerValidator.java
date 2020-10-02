package com.example.demo.customer.validation;

import com.example.demo.customer.dto.CustomerDTO;
import com.example.demo.customer.entity.Customer;
import com.example.demo.customer.repository.CustomerRepository;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.uitl.ValidateUtils;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CustomerValidator {

    public <ID> Customer getValidtor(@NonNull final String customerId,
                                     @NonNull CustomerRepository customerRepository) {
        //UUID validate
        final UUID id = ValidateUtils.UUIDFormat(customerId);
        //check customerRepository
        return customerRepository.findById(id)
                .orElseThrow(CustomerValidator::resourceNotFoundException);
    }

    private static RuntimeException resourceNotFoundException() {
        throw new ResourceNotFoundException("hello");
    }

    public <T> CustomerDTO createValidate(final T customerDTO) {
        final CustomerDTO customerDtoMapping = (CustomerDTO) customerDTO;
        return customerDtoMapping;
    }
}
