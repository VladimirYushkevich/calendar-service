package com.outfittery.calendar.services.impl;

import com.outfittery.calendar.models.Customer;
import com.outfittery.calendar.repositories.CustomerRepository;
import com.outfittery.calendar.services.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@AllArgsConstructor
@Transactional
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Optional<Customer> find(Long id) {
        return customerRepository.findById(id);
    }
}
