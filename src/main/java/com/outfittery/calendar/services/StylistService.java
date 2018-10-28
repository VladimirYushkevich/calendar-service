package com.outfittery.calendar.services;

import com.outfittery.calendar.dto.StylistAvailabilitySearch;
import com.outfittery.calendar.models.Stylist;
import com.outfittery.calendar.models.StylistAvailability;

import java.util.List;
import java.util.Optional;

public interface StylistService {

    Optional<Stylist> find(Long id);

    List<StylistAvailability> search(StylistAvailabilitySearch filter);

    StylistAvailability create(StylistAvailability stylistAvailability);
}
