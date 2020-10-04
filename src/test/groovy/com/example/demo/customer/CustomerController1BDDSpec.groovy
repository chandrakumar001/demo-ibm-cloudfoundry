package com.example.demo.customer

import com.example.demo.customer.dto.ResponseMessage
import groovyx.net.http.RESTClient
import spock.lang.Specification
import spock.lang.Unroll

import static groovyx.net.http.ContentType.JSON

class CustomerController1BDDSpec extends Specification {

    def restClient = new RESTClient("http://localhost:8080/")

    @Unroll
    def 'should  return 201 code (created) #uniqueIdValue'() {
        when: 'try to save record with all required fields'
        def response = restClient.post(
                path: 'v1/customers',
                body: [uniqueId: uniqueIdValue,
                       name    : nameValue,
                       address : addressValue],
                requestContentType: JSON)

        then: 'server returns 201 code (created)'
        final ResponseMessage messages = response.getData();
        assert response.status == 201;
        assert messages.response == 'created'
        where:
        testStep | uniqueIdValue  | nameValue      | addressValue
        'ok1'    | 'ch@gmail.com' | 'Chandrakumar' | 'Bangalore'
        'ok2'    | 'c@gmail.com'  | 'Kumar'        | 'Chennai'
    }
}