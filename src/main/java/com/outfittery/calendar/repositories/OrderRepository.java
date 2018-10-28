package com.outfittery.calendar.repositories;

import com.outfittery.calendar.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
