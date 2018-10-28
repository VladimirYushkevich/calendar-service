package com.outfittery.calendar.controllers;

import com.outfittery.calendar.dto.OrderDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.springframework.http.HttpStatus.CREATED;

public class OrderControllerIT extends BaseControllerIT {

    @Value("http://localhost:${local.server.port}/api/v1/order")
    private String base;

    private Date day = Date.from(LocalDate.parse("2018-10-22").atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

    @Test
    public void createOrder() {
        ResponseEntity<OrderDTO> response = template.postForEntity(base, from(1L, day, 1), OrderDTO.class);

        final OrderDTO orderDTO = Objects.requireNonNull(response.getBody());
        assertThat(response.getStatusCode(), equalTo(CREATED));
        assertThat(orderDTO.getId(), notNullValue());
    }

    private OrderDTO from(Long customerId, Date day, Integer timeSlotIndex) {
        return OrderDTO.builder()
                .customerId(customerId)
                .day(day)
                .timeSlotIndex(timeSlotIndex)
                .build();
    }
}
