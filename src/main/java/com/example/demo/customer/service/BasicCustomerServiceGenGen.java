package com.example.demo.customer.service;

import com.example.demo.customer.dto.CustomerDTO;
import com.example.demo.customer.entity.Customer;
import com.example.demo.customer.entity.ResponseMessage;
import com.example.demo.customer.entity.UniqueIdentity;
import com.example.demo.customer.repository.CustomerRepository;
import com.example.demo.customer.validation.CustomerValidator;
import com.example.demo.uitl.Objects;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class BasicCustomerServiceGenGen implements CustomerServiceGen {

    @NonNull
    private final CustomerValidator customerValidator;
    @NonNull
    private final CustomerRepository customerRepository;

    @Override
    public <T,R> R createCustomer(T t) {
        //validation
        final CustomerDTO customerDTO = customerValidator.createValidate(t);
        // mapping
        final Customer customer = new Customer();
        customer.setAddress(customerDTO.getAddress());
        final UniqueIdentity uniqueIdentity = new UniqueIdentity();
        uniqueIdentity.setEmailId(customerDTO.getUniqueId());
        customer.setUniqueId(uniqueIdentity);
        //saving customerRepository
        customerRepository.save(customer);
        final ResponseMessage created = ResponseMessage.of("created");
        return Objects.cast(created);
    }

    @Override
    public <T, R> R updateCustomer(T customer, String customerId) {
        return null;
    }

    @Override
    public <T> T getCustomer(@NonNull final String customerId) {
        //validation
        customerValidator.getValidtor(customerId, customerRepository);
        //mapping
        final UniqueIdentity uniqueIdentity = new UniqueIdentity();
        uniqueIdentity.setEmailId("ch@gmail.com");
        final Customer customer = new Customer();
        customer.setAddress("Bangalore");
        customer.setName("hc");
        customer.setUniqueId(uniqueIdentity);
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setName(customer.getName());
        customerDTO.setUniqueId(uniqueIdentity.getEmailId());
        return Objects.cast(customerDTO);

    }

    @Override
    public <R> R deleteCustomer(String customerId) {
        final UniqueIdentity uniqueIdentity = new UniqueIdentity();
        uniqueIdentity.setEmailId("ch@gmail.com");
        final Customer customer = new Customer();
        customer.setAddress("Bangalore");
        customer.setName("hc");
        customer.setUniqueId(uniqueIdentity);
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setName(customer.getName());
        customerDTO.setUniqueId(uniqueIdentity.getEmailId());
        return Objects.cast(customerDTO);
    }

    @Override
    public <T> List<T> getCustomers() {
        return null;
    }
}
