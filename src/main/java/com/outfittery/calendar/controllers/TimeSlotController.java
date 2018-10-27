package com.outfittery.calendar.controllers;

import com.outfittery.calendar.dto.TimeSlotDTO;
import com.outfittery.calendar.dto.TimeSlotSearch;
import com.outfittery.calendar.exception.NotFoundException;
import com.outfittery.calendar.models.Stylist;
import com.outfittery.calendar.models.TimeSlot;
import com.outfittery.calendar.services.StylistService;
import com.outfittery.calendar.services.TimeSlotService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.outfittery.calendar.utils.mappers.TimeSlotMapper.buildTimeSlot;
import static com.outfittery.calendar.utils.mappers.TimeSlotMapper.buildTimeSlotDTO;

@RestController
@RequestMapping("/api/v1/time-slot")
@AllArgsConstructor
@Slf4j
@Api(description = "CRUD operations for time slots")
public class TimeSlotController {

    private final TimeSlotService timeSlotService;
    private final StylistService stylistService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a new Time Slot.")
    public TimeSlotDTO createTimeSlot(@RequestBody TimeSlotDTO request) {
        log.debug("::createTimeSlot {}", request);

        final Stylist stylist = stylistService.find(request.getStylistId()).orElseThrow(NotFoundException::new);
        final TimeSlot timeSlot = buildTimeSlot(request);
        timeSlot.setStylist(stylist);

        return buildTimeSlotDTO(timeSlotService.create(timeSlot));
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Creates publication.")
    public List<TimeSlotDTO> search(@RequestBody TimeSlotSearch request) {
        log.debug("::search {}", request);

        return null;
    }
}
