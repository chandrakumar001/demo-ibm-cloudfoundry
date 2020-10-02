package com.example.demo.customer.service;


import lombok.NonNull;

import java.util.List;

public interface CustomerServiceGen {
    <T, R> R createCustomer(final T customerDto);

    <T, R> R updateCustomer(final T customer, final String customerId);

    <R> R getCustomer(@NonNull final String customerId);

    <R> R deleteCustomer(final String customerId);

    <T> List<T> getCustomers();
}
