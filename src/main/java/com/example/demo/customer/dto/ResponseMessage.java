package com.example.demo.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(staticName = "of")
@Getter
public class ResponseMessage {
    public String response;
}
