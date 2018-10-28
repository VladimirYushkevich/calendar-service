package com.outfittery.calendar.utils.mappers;

import com.outfittery.calendar.dto.OrderDTO;
import com.outfittery.calendar.models.Order;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * Utility class for mapping order to related DTOs and vice versa.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OrderMapper {

    public static Order buildOrder(OrderDTO dto) {
        Order order = new Order();

        copyProperties(dto, order);

        return order;
    }

    public static OrderDTO buildOrderDTO(Order order) {
        final OrderDTO dto = new OrderDTO();

        copyProperties(order, dto);
        dto.setCustomerId(order.getCustomer().getId());
        dto.setStylistId(order.getStylist().getId());

        return dto;
    }
}
