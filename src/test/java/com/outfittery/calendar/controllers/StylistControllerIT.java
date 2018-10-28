package com.outfittery.calendar.controllers;

import com.outfittery.calendar.dto.StylistAvailabilityDTO;
import com.outfittery.calendar.dto.StylistAvailabilitySearchDTO;
import com.outfittery.calendar.dto.StylistAvailabilitySearchResultsDTO;
import com.outfittery.calendar.dto.StylistDTO;
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

public class StylistControllerIT extends BaseControllerIT {

    @Value("http://localhost:${local.server.port}/api/v1/stylist")
    private String base;

    private Date today = Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

    @Test
    public void getStylistById() {
        ResponseEntity<StylistDTO> response = template.getForEntity(base + "/1", StylistDTO.class);

        final StylistDTO stylistDTO = Objects.requireNonNull(response.getBody());
        assertThat(response.getStatusCode(), equalTo(OK));
        assertThat(stylistDTO.getId(), equalTo(1L));
        assertThat(stylistDTO.getFirstName(), equalTo("Stylist Fn1"));
        assertThat(stylistDTO.getLastName(), equalTo("Stylist Ln1"));
    }

    @Test
    public void getStylistByIdNotFound() {
        ResponseEntity<StylistDTO> response = template.getForEntity(base + "/10001", StylistDTO.class);

        assertThat(response.getStatusCode(), equalTo(NOT_FOUND));
    }

    @Test
    public void createStylistAvailability() {
        ResponseEntity<StylistAvailabilityDTO> response = template.postForEntity(base + "/availability", from(4L, today), StylistAvailabilityDTO.class);

        final StylistAvailabilityDTO stylistAvailabilityDTO = Objects.requireNonNull(response.getBody());
        assertThat(response.getStatusCode(), equalTo(CREATED));
        assertThat(stylistAvailabilityDTO.getDay(), equalTo(today));
        assertThat(stylistAvailabilityDTO.getEncodedTimeSlots(), equalTo("0000000000000000"));
    }

    @Test
    public void shouldReturnConflictWhenStylistNotExists() {
        ResponseEntity<StylistAvailabilityDTO> response = template.postForEntity(base + "/availability", from(1004L, today), StylistAvailabilityDTO.class);

        assertThat(response.getStatusCode(), equalTo(CONFLICT));
    }

    @Test
    public void searchStylistAvailabilities() {
        final Date start = Date.from(LocalDate.parse("2018-10-22").atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        final Date end = Date.from(LocalDate.parse("2018-10-23").atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        final StylistAvailabilitySearchDTO filter = StylistAvailabilitySearchDTO.builder()
                .start(start)
                .end(end)
                .build();

        HttpEntity<StylistAvailabilitySearchDTO> request = new HttpEntity<>(filter, null);
        ParameterizedTypeReference<List<StylistAvailabilitySearchResultsDTO>> responseType = new ParameterizedTypeReference<List<StylistAvailabilitySearchResultsDTO>>() {
        };
        ResponseEntity<List<StylistAvailabilitySearchResultsDTO>> response = template.exchange(base + "/availability/search", HttpMethod.POST, request, responseType);

        final List<StylistAvailabilitySearchResultsDTO> stylistAvailabilityDTOS = Objects.requireNonNull(response.getBody());
        assertThat(response.getStatusCode(), equalTo(OK));
        assertThat(stylistAvailabilityDTOS.size(), is(2));
    }

    private StylistAvailabilityDTO from(Long id, Date day) {
        return StylistAvailabilityDTO.builder()
                .day(day)
                .stylistId(id)
                .build();
    }
}
