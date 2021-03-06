package com.example.demo.customer.service;

import com.example.demo.customer.dto.CustomerDTO;
import com.example.demo.customer.entity.Customer;
import com.example.demo.customer.mapper.CustomerMapper;
import com.example.demo.customer.repository.CustomerRepository;
import com.example.demo.customer.validation.CustomerValidate;
import com.example.demo.exception.ResourceConflictException;
import com.example.demo.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BasicCustomerQueryService implements CustomerQueryService {

    @NonNull
    private final CustomerRepository customerRepository;

    @Override
    public CustomerDTO getCustomer(@NonNull final String customerId) {

        //validation
        CustomerValidate.vaidateAndGetErrorMessage(customerId)
                .ifPresent(this::resourceNotFoundException);

        final UUID id = UUID.fromString(customerId);
        final Customer customer = getCustomerByIdIfThrowException(
                id
        );
        return CustomerMapper.mapToCustomerDTO(customer);
    }


    private Customer getCustomerByIdIfThrowException(final UUID id) {
        return getCustomerById(id)
                .orElseThrow(this::resourceNotFoundException);
    }

    private RuntimeException resourceNotFoundException() {
        return new ResourceNotFoundException("Not found");
    }

    private void resourceNotFoundException(final String customer) {
        throw new ResourceConflictException("Not found");
    }

    private Optional<Customer> getCustomerById(final UUID id) {
        return customerRepository.findById(id);
    }


    @Override
    public List<CustomerDTO> getAllCustomer() {

        final List<Customer> customerList = customerRepository.findAll();
        if (StringUtils.isEmpty(customerList)) {
            throw new RuntimeException("No record not found");
        }
        return customerList.stream()
                .map(CustomerMapper::mapToCustomerDTO)
                .collect(Collectors.toList());
    }
}
