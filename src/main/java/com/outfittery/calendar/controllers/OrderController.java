package com.outfittery.calendar.controllers;

import com.outfittery.calendar.dto.OrderDTO;
import com.outfittery.calendar.services.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.outfittery.calendar.utils.mappers.OrderMapper.buildOrderDTO;

@RestController
@RequestMapping("/api/v1/order")
@AllArgsConstructor
@Slf4j
@Api(description = "CRUD and business operations for orders")
public class OrderController {

    private final OrderService orderService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a new order")
    public OrderDTO createOrder(@RequestBody OrderDTO request) {
        log.debug("::createOrder {}", request);

        return buildOrderDTO(orderService.create(request));
    }
}
