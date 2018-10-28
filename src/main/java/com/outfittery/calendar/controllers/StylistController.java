package com.outfittery.calendar.controllers;

import com.outfittery.calendar.controllers.exceptions.NotFoundException;
import com.outfittery.calendar.dto.StylistDTO;
import com.outfittery.calendar.services.StylistService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.outfittery.calendar.utils.mappers.StylistMapper.buildStylistDTO;

@RestController
@RequestMapping("/api/v1/stylist")
@AllArgsConstructor
@Slf4j
@Api(description = "CRUD operations for stylists")
public class StylistController {

    private final StylistService stylistService;

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Finds stylist by id.")
    public StylistDTO getStylistById(@PathVariable("id") Long id) {
        log.debug("::getById {}", id);

        return buildStylistDTO(stylistService.find(id).orElseThrow(NotFoundException::new));
    }
}
