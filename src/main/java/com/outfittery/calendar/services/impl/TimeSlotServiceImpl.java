package com.outfittery.calendar.services.impl;

import com.outfittery.calendar.models.TimeSlot;
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

    @Override
    public Optional<TimeSlot> find(Long id) {
        return timeSlotRepository.findById(id);
    }
}
