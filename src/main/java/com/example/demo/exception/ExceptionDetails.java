package com.example.demo.exception;

import lombok.Value;

@Value(staticConstructor = "of")
public class ExceptionDetails {
    private String localTime;
    private String message;
}
