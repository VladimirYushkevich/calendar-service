package com.outfittery.calendar.repositories;

import com.outfittery.calendar.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
