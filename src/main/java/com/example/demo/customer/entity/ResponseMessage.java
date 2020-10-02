package com.example.demo.customer.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(staticName = "of")
@Getter
public class ResponseMessage {
    public String response;
}
