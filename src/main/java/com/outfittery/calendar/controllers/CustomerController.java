package com.outfittery.calendar.controllers;

import com.outfittery.calendar.dto.CustomerDTO;
import com.outfittery.calendar.exception.NotFoundException;
import com.outfittery.calendar.services.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.outfittery.calendar.utils.mappers.CustomerMapper.buildCustomerDTO;

@RestController
@RequestMapping("/api/v1/customer")
@AllArgsConstructor
@Slf4j
@Api(description = "CRUD operations for customers")
public class CustomerController {

    private final CustomerService customerService;

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Finds customer by id.")
    public CustomerDTO getCustomerById(@PathVariable("id") Long id) {
        log.debug("::getById {}", id);

        return buildCustomerDTO(customerService.find(id).orElseThrow(NotFoundException::new));
    }
}
