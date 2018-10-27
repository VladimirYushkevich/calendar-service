package com.outfittery.calendar.controllers;

import com.outfittery.calendar.dto.CustomerDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

public class CustomerControllerIT extends BaseControllerIT {

    @Value("http://localhost:${local.server.port}/api/v1/customer")
    private String base;

    @Test
    public void getCustomerById() {
        ResponseEntity<CustomerDTO> response = template.getForEntity(base + "/1", CustomerDTO.class);

        final CustomerDTO customerDTO = Objects.requireNonNull(response.getBody());
        assertThat(response.getStatusCode(), equalTo(OK));
        assertThat(customerDTO.getId(), equalTo(1L));
        assertThat(customerDTO.getFirstName(), equalTo("Customer Fn1"));
        assertThat(customerDTO.getLastName(), equalTo("Customer Ln1"));
    }

    @Test
    public void getCustomerByIdNotFound() {
        ResponseEntity<CustomerDTO> response = template.getForEntity(base + "/10001", CustomerDTO.class);

        assertThat(response.getStatusCode(), equalTo(NOT_FOUND));
    }
}
