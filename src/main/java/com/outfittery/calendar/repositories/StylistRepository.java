package com.outfittery.calendar.repositories;

import com.outfittery.calendar.models.Stylist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StylistRepository extends JpaRepository<Stylist, Long> {
}
