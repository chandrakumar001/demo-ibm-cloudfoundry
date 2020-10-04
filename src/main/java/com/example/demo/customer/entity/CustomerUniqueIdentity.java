package com.example.demo.customer.entity;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class CustomerUniqueIdentity {

    private String uniqueId;
}
