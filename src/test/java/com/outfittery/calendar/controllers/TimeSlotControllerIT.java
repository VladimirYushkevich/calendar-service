package com.outfittery.calendar.controllers;

import com.outfittery.calendar.dto.TimeSlotDTO;
import com.outfittery.calendar.dto.TimeSlotSearch;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.springframework.http.HttpStatus.*;

public class TimeSlotControllerIT extends BaseControllerIT {

    @Value("http://localhost:${local.server.port}/api/v1/time-slot")
    private String base;

    private Date today = Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

    @Test
    public void createTimeSlot() {
        ResponseEntity<TimeSlotDTO> response = template.postForEntity(base, from(4L, today), TimeSlotDTO.class);

        final TimeSlotDTO timeSlotDTO = Objects.requireNonNull(response.getBody());
        assertThat(response.getStatusCode(), equalTo(CREATED));
        assertThat(timeSlotDTO.getDay(), equalTo(today));
        assertThat(timeSlotDTO.getAvailability(), equalTo("0000000000000000"));
    }

    @Test
    public void shouldReturnConflictWhenStylistNotExists() {
        ResponseEntity<TimeSlotDTO> response = template.postForEntity(base, from(1004L, today), TimeSlotDTO.class);

        assertThat(response.getStatusCode(), equalTo(CONFLICT));
    }

    @Test
    public void searchTimeSlots() {
        final Date start = Date.from(LocalDate.parse("2018-10-22").atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        final Date end = Date.from(LocalDate.parse("2018-10-23").atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        final TimeSlotSearch filter = TimeSlotSearch.builder()
                .start(start)
                .end(end)
                .build();

        HttpEntity<TimeSlotSearch> request = new HttpEntity<>(filter, null);
        ParameterizedTypeReference<List<TimeSlotDTO>> responseType = new ParameterizedTypeReference<List<TimeSlotDTO>>() {
        };
        ResponseEntity<List<TimeSlotDTO>> response = template.exchange(base + "/search", HttpMethod.POST, request, responseType);

        final List<TimeSlotDTO> timeSlotDTOs = Objects.requireNonNull(response.getBody());
        assertThat(response.getStatusCode(), equalTo(OK));
        assertThat(timeSlotDTOs.size(), is(2));
    }

    private TimeSlotDTO from(Long id, Date day) {
        return TimeSlotDTO.builder()
                .day(day)
                .stylistId(id)
                .build();
    }
}
