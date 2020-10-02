package com.example.demo.customer

import com.example.demo.customer.entity.ResponseMessage
import groovyx.net.http.RESTClient
import spock.lang.Specification
import spock.lang.Unroll

import static groovyx.net.http.ContentType.JSON

class CustomerControllerBDDSpec extends Specification {

    def restClient = new RESTClient("http://localhost:8080/")

/*
    def 'should return 200 code when used valid credentials'() {
        when: 'login with invalid credentials'
        def response = restClient.get(path: '/basic-auth/user/pass')

        then: 'server returns 200 code (ok)'
        assert response.status == 200: 'response code should be 200 when tried to authenticate with valid credentials'
    }

    def 'should return 401 (unauthorized) code when used invalid credentials'() {
        when: 'login with invalid credentials'
        restClient.get(path: '/basic-auth/user/pass')

        then: 'server returns 401 code (unauthorized)'
        HttpResponseException e = thrown(HttpResponseException)
        assert e.response.status == 401: 'response code should be 401 when you use wrong credentials'
    }
*/
/*
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
    }*//*


    @Ignore
    def 'should return image metadata containing proper fields'() {

        when: 'send request for image metadata'
        def response = client.get(path: '/photos/1')

        then: 'server returns set of metadata for a single image'
        assert response.status == 200: 'response code should be 200'
        assert response.contentType == 'application/json': 'response should be in json format'

        and: 'response contains all required fields'
        assert response.data.albumId != null
        assert response.data.id != null
        assert response.data.title != null
        assert response.data.url != null
        assert response.data.thumbNailUrl != null
    }
*/

    @Unroll
    def 'should #uniqueIdValue return 201 code (created) when trying to save record with all requiredfields '() {
        when: 'try to save record with all required fields'
        def response = restClient.post(
                path: 'v1/customers',
                body: [uniqueId: uniqueIdValue,
                       name    : nameValue,
                       address : addressValue],
                requestContentType: JSON)

        then: 'server returns 201 code (created)'
        ResponseMessage messages = response.getData();
//'response code should be 201 if provided all required parameters'
        assert response.status == 201;
        assert messages.response == 'created'
        where:
        uniqueIdValue  | nameValue      | addressValue
        'ch@gmail.com' | 'Chandrakumar' | 'Bangalore'
       // 'c@gmail.com'  | 'Kumar'        | 'Chennai'

    }
/*
    def 'should return 400 code (bad request) if provided id parameter does not contain 3 digits'() {

        when: 'send request with incorrect id to get album metadata'
        client.get(path: '/albums', query: [id: idVal])

        then: 'server returns 400 code'
        HttpResponseException e = thrown(HttpResponseException)
        assert e.response.status == 400: 'response code should be 400 if provided incorrect album id parameter'

        where:
        idVal           | _
        -1              |
         0              |
         1              |
         15             |
         99             |
         0001           |
         9999           |
         99999999999991 |
    }*/
}