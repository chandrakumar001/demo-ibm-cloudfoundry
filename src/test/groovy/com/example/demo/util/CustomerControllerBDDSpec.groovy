package com.example.demo.util


import groovyx.net.http.RESTClient
import spock.lang.Specification

class CustomerControllerBDDSpec extends Specification {

    def restClient = new RESTClient("http://localhost:8080/")

    def "InCheck Arrivals controller"() {

        given:
        def id = "af15b7db-8cff-4e33-b297-00b228258af1"

        when: "get all arrivals"
        def response = restClient.get(path: 'v1/customers/' + id,
                contentType: 'application/json')

        then: "Status is 200"
        response.status == 200

        and: "Body contains proper values"
        assert response.data.address == 'Bangalore'
        assert response.data.name == 'hc'
        assert response.data.uniqueId == 'ch@gmail.com'
    }
}