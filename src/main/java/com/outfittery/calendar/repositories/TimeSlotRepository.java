package com.outfittery.calendar.repositories;

import com.outfittery.calendar.models.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {

    List<TimeSlot> findAllByDayBetweenAndAndAvailabilityContains(Date start, Date end, String available);
}
