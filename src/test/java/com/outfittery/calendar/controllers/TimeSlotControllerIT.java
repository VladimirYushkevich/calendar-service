package com.outfittery.calendar.controllers;

import com.outfittery.calendar.dto.TimeSlotDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.springframework.http.HttpStatus.OK;

public class TimeSlotControllerIT extends BaseControllerIT {

    @Value("http://localhost:${local.server.port}/api/v1/time-slot")
    private String base;

    @Test
    public void getTimeSlotById() {
        ResponseEntity<TimeSlotDTO> response = template.getForEntity(base + "/1", TimeSlotDTO.class);

        final TimeSlotDTO timeSlotDTO = Objects.requireNonNull(response.getBody());
        assertThat(response.getStatusCode(), equalTo(OK));
        assertThat(timeSlotDTO.getId(), equalTo(1L));
    }
}
