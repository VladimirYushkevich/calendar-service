package com.outfittery.calendar.controllers;

import com.outfittery.calendar.dto.OrderBulkDTO;
import com.outfittery.calendar.dto.OrderDTO;
import com.outfittery.calendar.services.OrderService;
import com.outfittery.calendar.utils.mappers.OrderMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.outfittery.calendar.utils.mappers.OrderMapper.buildOrderDTO;
import static java.util.stream.Collectors.toList;

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

    @RequestMapping(value = "/bulk", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a new order via bulk operation")
    public List<OrderDTO> createBulkOrders(@RequestBody OrderBulkDTO request) {
        log.debug("::createBulkOrders {}", request);

        return orderService.createBulk(request).stream()
                .map(OrderMapper::buildOrderDTO)
                .collect(toList());
    }
}
