package com.outfittery.calendar.controllers;

import com.outfittery.calendar.controllers.exceptions.NotFoundException;
import com.outfittery.calendar.dto.StylistAvailabilityDTO;
import com.outfittery.calendar.dto.StylistAvailabilitySearchDTO;
import com.outfittery.calendar.dto.StylistAvailabilitySearchResultsDTO;
import com.outfittery.calendar.dto.StylistDTO;
import com.outfittery.calendar.models.StylistAvailability;
import com.outfittery.calendar.services.StylistService;
import com.outfittery.calendar.utils.mappers.StylistAvailabilityMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.outfittery.calendar.utils.TimeSlotUtils.combineAvailability;
import static com.outfittery.calendar.utils.mappers.StylistAvailabilityMapper.buildStylistAvailability;
import static com.outfittery.calendar.utils.mappers.StylistAvailabilityMapper.buildStylistAvailabilityDTO;
import static com.outfittery.calendar.utils.mappers.StylistMapper.buildStylistDTO;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/v1/stylist")
@AllArgsConstructor
@Slf4j
@Api(description = "CRUD and bussiness operations for stylists")
public class StylistController {

    private final StylistService stylistService;

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Finds stylist by id.")
    public StylistDTO getStylistById(@PathVariable("id") Long id) {
        log.debug("::getById {}", id);

        return buildStylistDTO(stylistService.find(id).orElseThrow(NotFoundException::new));
    }

    @RequestMapping(value = "/availability", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a new Time Slot.")
    public StylistAvailabilityDTO createStylistAvailability(@RequestBody StylistAvailabilityDTO request) {
        log.debug("::createStylistAvailability {}", request);

        return buildStylistAvailabilityDTO(stylistService.create(buildStylistAvailability(request)));
    }

    @RequestMapping(value = "/availability/search", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Search for available time slots")
    public List<StylistAvailabilitySearchResultsDTO> searchStylistAvailabilities(@RequestBody StylistAvailabilitySearchDTO request) {
        log.debug("::searchStylistAvailabilities {}", request);

        final Map<Date, String> availabilityByDate = stylistService.search(request).stream()
                .collect(groupingBy(StylistAvailability::getDay, combineAvailability()));

        return availabilityByDate.entrySet().stream()
                .map(StylistAvailabilityMapper::buildStylistAvailabilityDTOFromEntry)
                .collect(toList());
    }
}
