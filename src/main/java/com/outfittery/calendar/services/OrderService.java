package com.outfittery.calendar.services;

import com.outfittery.calendar.dto.OrderBulkDTO;
import com.outfittery.calendar.dto.OrderDTO;
import com.outfittery.calendar.models.Order;

import java.util.List;

public interface OrderService {

    Order create(OrderDTO dto);

    List<Order> createBulk(OrderBulkDTO dto);
}
