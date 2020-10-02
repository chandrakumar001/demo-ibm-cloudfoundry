package com.example.demo

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

@Unroll
class DataDrivenSpec extends Specification {
    @Subject
    def arithmeticOperation = new ArithmeticOperation()

    def "can add"() {
        expect:
        arithmeticOperation.add(1, b) >= 2

        where:
        b << [1, 2, 3, 4, 5]
    }

    def "can add #a to #b and receive #result"() {
        expect:
        arithmeticOperation.add(a, b) == result

        where:
        a  | b  | result
        1  | 3  | 4
        3  | 4  | 7
        10 | 20 | 30
    }
}