package com.example.demo.customer.entity;

import com.example.demo.jpa.auditentity.Auditable;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Table(name = "customer", schema = "customer")
@Entity
@Data
public class Customer extends Auditable<String> {

    @Id
    @GeneratedValue
    private UUID customerId;
    private CustomerUniqueIdentity uniqueId;
    private String name;
    private String address;
    private boolean isDeleted = true;
}
