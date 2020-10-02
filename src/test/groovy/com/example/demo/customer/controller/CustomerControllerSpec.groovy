package com.example.demo.customer.controller

import com.example.demo.customer.dto.CustomerDTO
import com.example.demo.customer.service.CustomerServiceGen
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

class CustomerControllerSpec extends Specification {
    // Don't want to call real service, so use mock
    def customerService = Mock(CustomerServiceGen);
    // Init controller with mock:
    def api = new CustomerController(customerService)
    // Let Spring MVC Test process the controller:
    def mvc = MockMvcBuilders.standaloneSetup(api).build()
    ObjectMapper objectMapper=new ObjectMapper();

    def "when get is performed then the response has status 200"() {
        def response
        given:
        def id = "af15b7db-8cff-4e33-b297-00b228258af3"
        def customerDTO = new CustomerDTO()
        customerDTO.setName("chandrakumar")
        customerDTO.setAddress("bangalore")
        customerDTO.setUniqueId("chandrakumar@gmail.com")
        and:
        customerService.getCustomer(id) >> customerDTO

        when:
        response = mvc.perform(get('/v1/customers/' + id)
                .contentType('application/json'))
                .andReturn().response
        then:
        response.status == HttpStatus.OK.value()

        and: "Body contains proper values"
        def str=response.getContentAsString()
        response.getContentAsString() == '{"uniqueId":"chandrakumar@gmail.com","name":"chandrakumar","address":"bangalore"}'
    }
}