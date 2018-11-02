package com.outfittery.calendar.repositories;

import com.outfittery.calendar.models.Availability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {

    List<Availability> findAllByDayBetweenAndEncodedTimeSlotsContains(Date start, Date end, String availability);
}
