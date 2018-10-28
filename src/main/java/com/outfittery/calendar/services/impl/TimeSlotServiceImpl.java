package com.outfittery.calendar.services.impl;

import com.outfittery.calendar.dto.TimeSlotSearch;
import com.outfittery.calendar.models.TimeSlot;
import com.outfittery.calendar.repositories.TimeSlotRepository;
import com.outfittery.calendar.services.TimeSlotService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Component
@AllArgsConstructor
@Transactional
@Slf4j
public class TimeSlotServiceImpl implements TimeSlotService {

    private final TimeSlotRepository timeSlotRepository;

    @Override
    public List<TimeSlot> search(TimeSlotSearch filter) {
        final Date start = filter.getStart();
        final Date end = filter.getEnd();
        final List<TimeSlot> timeSlots = timeSlotRepository.findAllByDayBetweenAndAndAvailabilityContains(start, end, "0");
        log.debug("::found {} time slots", timeSlots.size());
        return timeSlots;
    }

    @Override
    public TimeSlot create(TimeSlot timeSlot) {
        final TimeSlot createdTimeSlot = timeSlotRepository.save(timeSlot);
        log.debug("::created {}", createdTimeSlot);
        return createdTimeSlot;
    }
}
