package com.outfittery.calendar.controllers;

import com.outfittery.calendar.dto.OrderBulkDTO;
import com.outfittery.calendar.dto.OrderDTO;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.springframework.http.HttpStatus.CONFLICT;
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
        assertThat(orderDTO.getStylistId(), notNullValue());
        assertThat(orderDTO.getTimeSlot(), is("09:30"));
        assertThat(orderDTO.getCustomerId(), is(1L));
    }

    @Test
    public void shouldReturnConflictWhenNoStylistsAvailableForTimeSlot() {
        ResponseEntity<OrderDTO> response = template.postForEntity(base, from(1L, day, 0), OrderDTO.class);

        assertThat(response.getStatusCode(), equalTo(CONFLICT));
    }

    @Test
    public void bulkOrderCreation() {
        final Date start = Date.from(LocalDate.parse("2018-10-23").atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        final Date end = Date.from(LocalDate.parse("2018-10-24").atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        final Set<Long> customerIds = new HashSet<>(Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L));
        final OrderBulkDTO orderBulkDTO = OrderBulkDTO.builder()
                .start(start)
                .end(end)
                .customerIds(customerIds)
                .build();

        HttpEntity<OrderBulkDTO> request = new HttpEntity<>(orderBulkDTO, null);
        ParameterizedTypeReference<List<OrderDTO>> responseType = new ParameterizedTypeReference<List<OrderDTO>>() {
        };
        ResponseEntity<List<OrderDTO>> response = template.exchange(base + "/bulk", HttpMethod.POST, request, responseType);

        final List<OrderDTO> OrderDTOs = Objects.requireNonNull(response.getBody());
        assertThat(response.getStatusCode(), equalTo(CREATED));
        assertThat(OrderDTOs.size(), Matchers.is(12));
    }

    private OrderDTO from(Long customerId, Date day, Integer timeSlotIndex) {
        return OrderDTO.builder()
                .customerId(customerId)
                .day(day)
                .timeSlotIndex(timeSlotIndex)
                .build();
    }
}
