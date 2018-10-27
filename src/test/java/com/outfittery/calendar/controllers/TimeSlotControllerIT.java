package com.outfittery.calendar.controllers;

import com.outfittery.calendar.dto.TimeSlotDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.springframework.http.HttpStatus.CREATED;

public class TimeSlotControllerIT extends BaseControllerIT {

    @Value("http://localhost:${local.server.port}/api/v1/time-slot")
    private String base;

    private Date today = Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

    @Test
    public void createTimeSlot() {
        final TimeSlotDTO dto = TimeSlotDTO.builder()
                .day(today)
                .stylistId(1L)
                .build();

        ResponseEntity<TimeSlotDTO> response = template.postForEntity(base, dto, TimeSlotDTO.class);
        final TimeSlotDTO timeSlotDTO = Objects.requireNonNull(response.getBody());
        assertThat(response.getStatusCode(), equalTo(CREATED));
        assertThat(timeSlotDTO.getDay(), equalTo(today));
        assertThat(timeSlotDTO.getAvailability(), equalTo("0000000000000000"));
    }
}
