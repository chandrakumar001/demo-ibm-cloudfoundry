package com.example.demo.customer.service;

import com.example.demo.customer.dto.CustomerDTO;
import com.example.demo.customer.dto.ResponseMessage;
import com.example.demo.customer.entity.Customer;
import com.example.demo.customer.mapper.CustomerMapper;
import com.example.demo.customer.repository.CustomerRepository;
import com.example.demo.exception.ResourceConflictException;
import com.example.demo.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BasicCustomerCommandService implements CustomerCommandService {

    public static final String THE_CUSTOMER_ID_HAS_BEEN_DELETED = "The customer id has been deleted : ";

    @NonNull
    private final CustomerRepository customerRepository;

    private static void resourceConflictException(final Customer customer) {
        throw new ResourceConflictException("Already exists id");
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {

        //validation
        customerRepository.findByUniqueIdUniqueId(customerDTO.getUniqueId())
                .ifPresent(BasicCustomerCommandService::resourceConflictException);

        // mapping
        final Customer customer = CustomerMapper.mapToCustomer(
                customerDTO
        );


        //saving customerRepository
        final Customer customerDB = customerRepository.save(
                customer
        );
        return CustomerMapper.mapToCustomerDTO(customerDB);
    }

    @Override
    public CustomerDTO updateCustomer(final CustomerDTO customerDTO,
                                      final String customerId) {

        final UUID id = UUID.fromString(customerId);
        final Customer customer = resourceNotFoundException(
                id
        );
        customer(customerDTO, customer);

        final Customer customerDB = customerRepository.save(
                customer
        );
        return CustomerMapper.mapToCustomerDTO(customerDB);
    }

    public void customer(final CustomerDTO customerDTO,
                         final Customer customer) {

        customer.setName(customerDTO.getName());
        customer.setAddress(customerDTO.getAddress());
    }


    private Customer resourceNotFoundException(final UUID id) {
        return getCustomerById(id)
                .orElseThrow(this::resourceNotFoundException);
    }

    private RuntimeException resourceNotFoundException() {
        return new ResourceNotFoundException("Not found");
    }

    private Optional<Customer> getCustomerById(final UUID id) {
        return customerRepository.findById(id);
    }

    @Override
    public ResponseMessage deleteCustomer(final String customerId) {

        customerRepository.softDelete(UUID.fromString(customerId));
        return ResponseMessage.of(THE_CUSTOMER_ID_HAS_BEEN_DELETED + customerId);
    }
}
