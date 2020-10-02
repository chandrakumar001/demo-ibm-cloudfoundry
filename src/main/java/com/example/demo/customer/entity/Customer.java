package com.example.demo.customer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Table
@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue
    private UUID customerId;
    private UniqueIdentity uniqueId;
    private String name;
    private String address;
}
