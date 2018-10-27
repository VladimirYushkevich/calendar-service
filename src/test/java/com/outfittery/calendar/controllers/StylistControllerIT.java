package com.outfittery.calendar.controllers;

import com.outfittery.calendar.dto.StylistDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

public class StylistControllerIT extends BaseControllerIT {

    @Value("http://localhost:${local.server.port}/api/v1/stylist")
    private String base;

    @Test
    public void getStylistById() {
        ResponseEntity<StylistDTO> response = template.getForEntity(base + "/1", StylistDTO.class);

        final StylistDTO stylistDTO = Objects.requireNonNull(response.getBody());
        assertThat(response.getStatusCode(), equalTo(OK));
        assertThat(stylistDTO.getId(), equalTo(1L));
        assertThat(stylistDTO.getFirstName(), equalTo("Calvin"));
        assertThat(stylistDTO.getLastName(), equalTo("Klein"));
    }

    @Test
    public void getStylistByIdNotFound() {
        ResponseEntity<StylistDTO> response = template.getForEntity(base + "/10001", StylistDTO.class);

        assertThat(response.getStatusCode(), equalTo(NOT_FOUND));
    }
}
