package com.outfittery.calendar.controllers;

import com.outfittery.calendar.dto.TimeSlotDTO;
import com.outfittery.calendar.exception.NotFoundException;
import com.outfittery.calendar.services.TimeSlotService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.outfittery.calendar.utils.mappers.TimeSlotMapper.buildTimeSlotDTO;

@RestController
@RequestMapping("/api/v1/time-slot")
@AllArgsConstructor
@Slf4j
@Api(description = "CRUD operations for time slots")
public class TimeSlotController {

    private final TimeSlotService timeSlotService;

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Finds time slot by id.")
    public TimeSlotDTO getTimeSlotById(@PathVariable("id") Long id) {
        log.debug("::getById {}", id);

        return buildTimeSlotDTO(timeSlotService.find(id).orElseThrow(NotFoundException::new));
    }
}
