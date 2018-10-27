package com.outfittery.calendar.services;

import com.outfittery.calendar.models.Customer;

import java.util.Optional;

public interface CustomerService {

    Optional<Customer> find(Long id);
}
