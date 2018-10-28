package com.outfittery.calendar.controllers;

import com.outfittery.calendar.dto.AvailabilityDTO;
import com.outfittery.calendar.dto.AvailabilitySearchDTO;
import com.outfittery.calendar.dto.AvailabilitySearchResultsDTO;
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
import static org.hamcrest.Matchers.notNullValue;
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
        assertThat(stylistDTO.getAvailabilityIds().size(), is(3));
    }

    @Test
    public void getStylistByIdNotFound() {
        ResponseEntity<StylistDTO> response = template.getForEntity(base + "/10001", StylistDTO.class);

        assertThat(response.getStatusCode(), equalTo(NOT_FOUND));
    }

    @Test
    public void createStylistAvailability() {
        ResponseEntity<AvailabilityDTO> availabilityResponse = template.postForEntity(base + "/availability",
                from(4L, today), AvailabilityDTO.class);

        final AvailabilityDTO availabilityDTO = Objects.requireNonNull(availabilityResponse.getBody());
        assertThat(availabilityResponse.getStatusCode(), equalTo(CREATED));
        assertThat(availabilityDTO.getId(), notNullValue());
        assertThat(availabilityDTO.getDay(), equalTo(today));
        assertThat(availabilityDTO.getEncodedTimeSlots(), equalTo("0000000000000000"));

        ResponseEntity<StylistDTO> stylistResponse = template.getForEntity(base + "/4", StylistDTO.class);

        final StylistDTO stylistDTO = Objects.requireNonNull(stylistResponse.getBody());
        assertThat(stylistResponse.getStatusCode(), equalTo(OK));
        assertThat(stylistDTO.getAvailabilityIds().size(), is(1));
    }

    @Test
    public void shouldReturnNotFoundWhenStylistNotExists() {
        ResponseEntity<AvailabilityDTO> response = template.postForEntity(base + "/availability",
                from(1004L, today), AvailabilityDTO.class);

        assertThat(response.getStatusCode(), equalTo(NOT_FOUND));
    }

    @Test
    public void searchStylistAvailabilities() {
        final Date start = Date.from(LocalDate.parse("2018-10-22").atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        final Date end = Date.from(LocalDate.parse("2018-10-23").atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        final AvailabilitySearchDTO filter = AvailabilitySearchDTO.builder()
                .start(start)
                .end(end)
                .build();

        HttpEntity<AvailabilitySearchDTO> request = new HttpEntity<>(filter, null);
        ParameterizedTypeReference<List<AvailabilitySearchResultsDTO>> responseType =
                new ParameterizedTypeReference<List<AvailabilitySearchResultsDTO>>() {
                };
        ResponseEntity<List<AvailabilitySearchResultsDTO>> response = template.exchange(base + "/availability/search",
                HttpMethod.POST, request, responseType);

        final List<AvailabilitySearchResultsDTO> stylistAvailabilityDTOs = Objects.requireNonNull(response.getBody());
        assertThat(response.getStatusCode(), equalTo(OK));
        assertThat(stylistAvailabilityDTOs.size(), is(2));
    }

    private AvailabilityDTO from(Long stylistId, Date day) {
        return AvailabilityDTO.builder()
                .day(day)
                .stylistId(stylistId)
                .build();
    }
}
