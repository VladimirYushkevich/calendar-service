package com.outfittery.calendar.services.impl;

import com.outfittery.calendar.controllers.exceptions.ConflictException;
import com.outfittery.calendar.controllers.exceptions.NotFoundException;
import com.outfittery.calendar.dto.OrderBulkDTO;
import com.outfittery.calendar.dto.OrderDTO;
import com.outfittery.calendar.models.Availability;
import com.outfittery.calendar.models.Customer;
import com.outfittery.calendar.models.Order;
import com.outfittery.calendar.models.Stylist;
import com.outfittery.calendar.repositories.AvailabilityRepository;
import com.outfittery.calendar.repositories.CustomerRepository;
import com.outfittery.calendar.repositories.OrderRepository;
import com.outfittery.calendar.services.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.outfittery.calendar.utils.TimeSlotUtils.*;
import static com.outfittery.calendar.utils.mappers.OrderMapper.buildOrder;
import static java.util.stream.Collectors.toList;

@Component
@AllArgsConstructor
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final AvailabilityRepository availabilityRepository;

    //TODO here should be used proper isolation level
    @Override
    public Order create(OrderDTO dto) {
        final Customer customer = customerRepository.findById(dto.getCustomerId()).orElseThrow(NotFoundException::new);
        log.debug("::found joined {}", customer);

        final Date day = dto.getDay();
        final Integer timeSlotIndex = dto.getTimeSlotIndex();
        final Availability availability = availabilityRepository.findAllByDayBetweenAndAndEncodedTimeSlotsContains(day, day, "0").stream()
                .filter(a -> a.getEncodedTimeSlots().charAt(timeSlotIndex) == '0')
                .findFirst().orElseThrow(() -> new ConflictException("Sorry, no more availability for this time slot. Please try another one."));
        log.debug("::found {}", availability);

        availability.setEncodedTimeSlots(updateEncodedTimeAvailability(availability.getEncodedTimeSlots(), timeSlotIndex, "1"));

        final Order order = buildOrder(dto);
        order.setCustomer(customer);
        order.setStylist(availability.getStylist());
        order.setTimeSlot(getTimeSlotsFromTimeIntervals().get(timeSlotIndex));
        final Order createdOrder = orderRepository.save(order);
        log.debug("::created {}", order);
        return createdOrder;
    }

    //TODO here should be used proper isolation level. Important to check numberOfCustomers < time slots in availabilities
    @Override
    public List<Order> createBulk(OrderBulkDTO dto) {
        final List<Customer> customers = customerRepository.findAllById(dto.getCustomerIds());
        final int numberOfCustomers = customers.size();
        log.debug("::found {} customers", numberOfCustomers);

        final AtomicInteger limit = new AtomicInteger(numberOfCustomers);
        final Date start = dto.getStart();
        final Date end = dto.getEnd();
        final List<Order> orders = availabilityRepository.findAllByDayBetweenAndAndEncodedTimeSlotsContains(start, end, "0").stream()
                .flatMap(a -> buildOrders(a, limit))
                .limit(numberOfCustomers)
                .collect(toList());
        IntStream.range(0, numberOfCustomers)
                .boxed()
                .forEach(i -> orders.get(i).setCustomer(customers.get(i)));

        orderRepository.saveAll(orders);
        log.debug("::created {} orders", orders);

        return orders;
    }

    private Stream<Order> buildOrders(Availability availability, AtomicInteger limit) {
        final String encodedTimeSlots = availability.getEncodedTimeSlots();
        final Date day = availability.getDay();
        final Stylist stylist = availability.getStylist();

        final int newLimit = limit.getAndSet(limit.intValue() - getNumberOfAvailableTimeSlots(encodedTimeSlots));

        return getTimeSlotsFromEncodedString(encodedTimeSlots).entrySet().stream()
                .map(e -> buildOrderAndUpdateAvailability(day, stylist, e, availability))
                .limit(newLimit);
    }

    private Order buildOrderAndUpdateAvailability(Date day, Stylist stylist, Map.Entry<Integer, String> entry, Availability availability) {
        final String encodedTimeSlots = availability.getEncodedTimeSlots();
        availability.setEncodedTimeSlots(updateEncodedTimeAvailability(encodedTimeSlots, entry.getKey(), "1"));

        return Order.builder().day(day).stylist(stylist).timeSlot(entry.getValue()).build();
    }
}
