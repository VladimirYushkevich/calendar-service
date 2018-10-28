package com.outfittery.calendar.utils.mappers;

import com.outfittery.calendar.dto.StylistDTO;
import com.outfittery.calendar.models.Stylist;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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

        return dto;
    }
}
