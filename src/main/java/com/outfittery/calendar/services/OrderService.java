package com.outfittery.calendar.services;

import com.outfittery.calendar.dto.OrderDTO;
import com.outfittery.calendar.models.Order;

public interface OrderService {

    Order create(OrderDTO order);
}
