package com.outfittery.calendar.services;

import com.outfittery.calendar.dto.TimeSlotSearch;
import com.outfittery.calendar.models.TimeSlot;

import java.util.List;

public interface TimeSlotService {

    List<TimeSlot> search(TimeSlotSearch filter);

    TimeSlot create(TimeSlot timeSlot);
}
