package com.outfittery.calendar.repositories;

import com.outfittery.calendar.models.StylistAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface StylistAvailabilityRepository extends JpaRepository<StylistAvailability, Long> {

    List<StylistAvailability> findAllByDayBetweenAndAndEncodedTimeSlotsContains(Date start, Date end, String availability);
}
