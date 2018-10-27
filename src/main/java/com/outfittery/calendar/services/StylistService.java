package com.outfittery.calendar.services;

import com.outfittery.calendar.models.Stylist;

import java.util.Optional;

public interface StylistService {

    Optional<Stylist> find(Long id);
}
