package com.outfittery.calendar.services;

import com.outfittery.calendar.dto.StylistAvailabilitySearchDTO;
import com.outfittery.calendar.models.Stylist;
import com.outfittery.calendar.models.StylistAvailability;

import java.util.List;
import java.util.Optional;

public interface StylistService {

    Optional<Stylist> find(Long id);

    List<StylistAvailability> search(StylistAvailabilitySearchDTO filter);

    StylistAvailability create(StylistAvailability stylistAvailability);
}
