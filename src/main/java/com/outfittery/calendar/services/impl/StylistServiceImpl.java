package com.outfittery.calendar.services.impl;

import com.outfittery.calendar.controllers.exceptions.ConflictException;
import com.outfittery.calendar.dto.AvailabilitySearchDTO;
import com.outfittery.calendar.models.Availability;
import com.outfittery.calendar.models.Stylist;
import com.outfittery.calendar.repositories.AvailabilityRepository;
import com.outfittery.calendar.repositories.StylistRepository;
import com.outfittery.calendar.services.StylistService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
@Transactional
@Slf4j
public class StylistServiceImpl implements StylistService {

    private final StylistRepository stylistRepository;
    private final AvailabilityRepository availabilityRepository;

    @Override
    public Optional<Stylist> find(Long id) {
        return stylistRepository.findById(id);
    }

    @Override
    public List<Availability> search(AvailabilitySearchDTO filter) {
        final Date start = filter.getStart();
        final Date end = filter.getEnd();
        final List<Availability> stylistAvailabilities = availabilityRepository.findAllByDayBetweenAndAndEncodedTimeSlotsContains(start, end, "0");
        log.debug("::found {} availabilities", stylistAvailabilities.size());
        return stylistAvailabilities;
    }

    @Override
    public Availability create(Availability availability) {
        final Stylist stylist = stylistRepository.findById(availability.getStylist().getId()).orElseThrow(ConflictException::new);
        availability.setStylist(stylist);
        final Availability createdAvailability = availabilityRepository.save(availability);
        log.debug("::created {}", createdAvailability);
        return createdAvailability;
    }
}
