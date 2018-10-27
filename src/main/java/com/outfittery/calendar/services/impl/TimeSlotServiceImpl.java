package com.outfittery.calendar.services.impl;

import com.outfittery.calendar.models.TimeSlot;
import com.outfittery.calendar.repositories.StylistRepository;
import com.outfittery.calendar.repositories.TimeSlotRepository;
import com.outfittery.calendar.services.TimeSlotService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@AllArgsConstructor
@Transactional
@Slf4j
public class TimeSlotServiceImpl implements TimeSlotService {

    private final TimeSlotRepository timeSlotRepository;
    private final StylistRepository stylistRepository;

    @Override
    public Optional<TimeSlot> find(Long id) {
        return timeSlotRepository.findById(id);
    }

    @Override
    public TimeSlot create(TimeSlot timeSlot) {
        final TimeSlot createdTimeSlot = timeSlotRepository.save(timeSlot);
        log.debug("::created {}", createdTimeSlot);
        return createdTimeSlot;
    }
}
