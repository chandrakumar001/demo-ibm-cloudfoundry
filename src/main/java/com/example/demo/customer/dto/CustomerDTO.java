package com.example.demo.customer.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
public class CustomerDTO {
    private String uniqueId;
    private String name;
    private String address;
}
