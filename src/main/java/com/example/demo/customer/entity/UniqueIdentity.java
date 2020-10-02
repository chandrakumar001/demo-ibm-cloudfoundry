package com.example.demo.customer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class UniqueIdentity {
    private String emailId;
    private String mobileNumber;
    private String socialId;
}
