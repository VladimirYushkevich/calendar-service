package com.outfittery.calendar.services;

import com.outfittery.calendar.models.TimeSlot;

import java.util.Optional;

public interface TimeSlotService {

    Optional<TimeSlot> find(Long id);
}
