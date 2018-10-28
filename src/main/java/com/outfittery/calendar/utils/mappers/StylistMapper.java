package com.outfittery.calendar.utils.mappers;

import com.outfittery.calendar.dto.StylistDTO;
import com.outfittery.calendar.models.Stylist;
import com.outfittery.calendar.models.StylistAvailability;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * Utility class for mapping stylist to related DTOs and vice versa.
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StylistMapper {

    public static Stylist buildStylist(StylistDTO dto) {
        Stylist stylist = new Stylist();

        copyProperties(dto, stylist);

        return stylist;
    }

    public static StylistDTO buildStylistDTO(Stylist stylist) {
        final StylistDTO dto = new StylistDTO();

        copyProperties(stylist, dto);
        final List<Long> availabilitiesIds = stylist.getAvailabilities().stream()
                .map(StylistAvailability::getId)
                .collect(toList());
        dto.setAvailabilityIds(availabilitiesIds);

        return dto;
    }
}
