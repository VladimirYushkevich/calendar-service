package com.outfittery.calendar.services;

import com.outfittery.calendar.dto.AvailabilitySearchDTO;
import com.outfittery.calendar.models.Availability;
import com.outfittery.calendar.models.Stylist;

import java.util.List;
import java.util.Optional;

public interface StylistService {

    Optional<Stylist> find(Long id);

    List<Availability> search(AvailabilitySearchDTO filter);

    Availability create(Availability availability);
}
