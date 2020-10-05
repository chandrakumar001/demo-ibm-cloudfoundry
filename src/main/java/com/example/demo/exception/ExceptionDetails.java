package com.example.demo.exception;

import lombok.Value;

@Value(staticConstructor = "of")
public class ExceptionDetails {

    String localTime;
    String code;
    String message;
}
