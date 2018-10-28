package com.outfittery.calendar.services.impl;

import com.outfittery.calendar.controllers.exceptions.ConflictException;
import com.outfittery.calendar.controllers.exceptions.NotFoundException;
import com.outfittery.calendar.dto.OrderDTO;
import com.outfittery.calendar.models.Availability;
import com.outfittery.calendar.models.Customer;
import com.outfittery.calendar.models.Order;
import com.outfittery.calendar.repositories.AvailabilityRepository;
import com.outfittery.calendar.repositories.CustomerRepository;
import com.outfittery.calendar.repositories.OrderRepository;
import com.outfittery.calendar.services.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static com.outfittery.calendar.utils.TimeSlotUtils.getTimeSlotsFromTimeIntervals;
import static com.outfittery.calendar.utils.TimeSlotUtils.updateEncodedTimeAvailability;
import static com.outfittery.calendar.utils.mappers.OrderMapper.buildOrder;

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
        availabilityRepository.save(availability);
        log.debug("::updated {}", availability);

        final Order order = buildOrder(dto);
        order.setCustomer(customer);
        order.setStylist(availability.getStylist());
        order.setTimeSlot(getTimeSlotsFromTimeIntervals().get(timeSlotIndex));
        final Order createdOrder = orderRepository.save(order);
        log.debug("::created {}", order);
        return createdOrder;
    }
}
