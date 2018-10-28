package com.outfittery.calendar.utils.mappers;

import com.outfittery.calendar.dto.StylistAvailabilityDTO;
import com.outfittery.calendar.models.StylistAvailability;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * Utility class for mapping time slot to related DTOs and vice versa.
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StylistAvailabilityMapper {

    public static StylistAvailability buildStylistAvailability(StylistAvailabilityDTO dto) {
        StylistAvailability stylistAvailability = new StylistAvailability();

        copyProperties(dto, stylistAvailability);

        return stylistAvailability;
    }

    public static StylistAvailabilityDTO buildStylistAvailabilityDTO(StylistAvailability stylistAvailability) {
        final StylistAvailabilityDTO dto = new StylistAvailabilityDTO();

        copyProperties(stylistAvailability, dto);

        return dto;
    }

    public static StylistAvailabilityDTO buildStylistAvailabilityDTOFromEntry(Map.Entry<Date, String> entry) {
        return StylistAvailabilityDTO.builder().day(entry.getKey()).encodedTimeSlots(entry.getValue()).build();
    }
}
