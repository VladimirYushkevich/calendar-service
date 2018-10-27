package com.outfittery.calendar.services.impl;

import com.outfittery.calendar.models.Stylist;
import com.outfittery.calendar.repositories.StylistRepository;
import com.outfittery.calendar.services.StylistService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@AllArgsConstructor
@Transactional
@Slf4j
public class StylistServiceImpl implements StylistService {

    private final StylistRepository stylistRepository;

    @Override
    public Optional<Stylist> find(Long id) {
        return stylistRepository.findById(id);
    }
}
