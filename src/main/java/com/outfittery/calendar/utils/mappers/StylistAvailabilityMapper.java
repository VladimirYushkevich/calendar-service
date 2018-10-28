package com.outfittery.calendar.utils.mappers;

import com.outfittery.calendar.dto.StylistAvailabilityDTO;
import com.outfittery.calendar.dto.StylistAvailabilitySearchResultsDTO;
import com.outfittery.calendar.dto.TimeSlotDTO;
import com.outfittery.calendar.models.Stylist;
import com.outfittery.calendar.models.StylistAvailability;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.outfittery.calendar.utils.TimeSlotUtils.getTimeSlotsFromEncodedString;
import static java.util.stream.Collectors.toList;
import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * Utility class for mapping time slot to related DTOs and vice versa.
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StylistAvailabilityMapper {

    public static StylistAvailability buildStylistAvailability(StylistAvailabilityDTO dto) {
        StylistAvailability stylistAvailability = new StylistAvailability();

        copyProperties(dto, stylistAvailability);
        stylistAvailability.setStylist(Stylist.builder().id(dto.getStylistId()).build());

        return stylistAvailability;
    }

    public static StylistAvailabilityDTO buildStylistAvailabilityDTO(StylistAvailability stylistAvailability) {
        final StylistAvailabilityDTO dto = new StylistAvailabilityDTO();

        copyProperties(stylistAvailability, dto);

        return dto;
    }

    public static StylistAvailabilitySearchResultsDTO buildStylistAvailabilityDTOFromEntry(Map.Entry<Date, String> entry) {
        final List<TimeSlotDTO> timeSlotDTOS = getTimeSlotsFromEncodedString(entry.getValue()).entrySet().stream()
                .map(e -> TimeSlotDTO.builder().index(e.getKey()).time(e.getValue()).build())
                .collect(toList());
        return StylistAvailabilitySearchResultsDTO.builder().day(entry.getKey()).timeSlots(timeSlotDTOS).build();
    }
}
