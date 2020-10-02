package com.example.demo.exception;

public class InvalidParam extends RuntimeException {
    public InvalidParam(String ok) {
        super(ok);
    }
}
