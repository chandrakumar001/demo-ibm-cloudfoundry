package com.example.demo.util

import com.example.demo.customer.dto.CustomerDTO
import com.example.demo.customer.entity.Customer
import com.example.demo.customer.repository.CustomerRepository
import com.example.demo.customer.service.BasicCustomerCommandService

import spock.lang.Specification

class CustomerValidationSpec extends Specification {
    def customerValidator=new CustomerValidator()
    def "CustomerId is null"() {
        def customerValidator = Mock(CustomerValidator)
        def customerRepository = Mock(CustomerRepository)

        def basicCustomerService = new BasicCustomerCommandService(customerValidator, customerRepository);
        given: "test"
        when: "when"
        Customer customer = basicCustomerService.mapToCustomer(null);
        then:
        def exception = thrown(NullPointerException)
        exception.message == 'customerId is marked non-null but is null'
    }

    def "CustomerId is ok"() {
        def customerValidator = Mock(CustomerValidator)
        def customerRepository = Mock(CustomerRepository)

        def basicCustomerService = new BasicCustomerCommandService(customerValidator, customerRepository)
        given: "test"
        when: "when"
        CustomerDTO customerDTO = basicCustomerService.mapToCustomer("null");
        then:
        customerDTO != null;
    }

    def "customerService is null"() {
        given: "test"
        when: "when"
        CustomerDTO customerDTO = customerValidator.getValidtor("id", null)
        then:
        def exception = thrown(NullPointerException)
        exception.message == 'customerRepository is marked non-null but is null'
    }

    def "Repostory is null"() {
        given: "test"
        when: "when"
        Customer customer = customerValidator.getValidtor("id", null)
        then:
        def exception = thrown(NullPointerException)
        exception.message == 'customerRepository is marked non-null but is null'
    }
}
