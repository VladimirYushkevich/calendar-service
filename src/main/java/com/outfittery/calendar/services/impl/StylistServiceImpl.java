package com.outfittery.calendar.services.impl;

import com.outfittery.calendar.controllers.exceptions.ConflictException;
import com.outfittery.calendar.dto.StylistAvailabilitySearchDTO;
import com.outfittery.calendar.models.Stylist;
import com.outfittery.calendar.models.StylistAvailability;
import com.outfittery.calendar.repositories.StylistAvailabilityRepository;
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
    private final StylistAvailabilityRepository stylistAvailabilityRepository;

    @Override
    public Optional<Stylist> find(Long id) {
        return stylistRepository.findById(id);
    }

    @Override
    public List<StylistAvailability> search(StylistAvailabilitySearchDTO filter) {
        final Date start = filter.getStart();
        final Date end = filter.getEnd();
        final List<StylistAvailability> stylistAvailabilities = stylistAvailabilityRepository.findAllByDayBetweenAndAndEncodedTimeSlotsContains(start, end, "0");
        log.debug("::found {} availabilities", stylistAvailabilities.size());
        return stylistAvailabilities;
    }

    @Override
    public StylistAvailability create(StylistAvailability stylistAvailability) {
        final Stylist stylist = stylistRepository.findById(stylistAvailability.getStylist().getId()).orElseThrow(ConflictException::new);
        stylistAvailability.setStylist(stylist);
        final StylistAvailability createdStylistAvailability = stylistAvailabilityRepository.save(stylistAvailability);
        log.debug("::created {}", createdStylistAvailability);
        return createdStylistAvailability;
    }
}
