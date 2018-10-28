package com.outfittery.calendar.utils.mappers;

import com.outfittery.calendar.dto.AvailabilityDTO;
import com.outfittery.calendar.dto.AvailabilitySearchResultsDTO;
import com.outfittery.calendar.dto.TimeSlotDTO;
import com.outfittery.calendar.models.Availability;
import com.outfittery.calendar.models.Stylist;
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
public final class AvailabilityMapper {

    public static Availability buildAvailability(AvailabilityDTO dto) {
        Availability availability = new Availability();

        copyProperties(dto, availability);
        availability.setStylist(Stylist.builder().id(dto.getStylistId()).build());

        return availability;
    }

    public static AvailabilityDTO buildAvailabilityDTO(Availability availability) {
        final AvailabilityDTO dto = new AvailabilityDTO();

        copyProperties(availability, dto);

        return dto;
    }

    public static AvailabilitySearchResultsDTO buildAvailabilityDTOFromEntry(Map.Entry<Date, String> entry) {
        final List<TimeSlotDTO> timeSlotDTOS = getTimeSlotsFromEncodedString(entry.getValue()).entrySet().stream()
                .map(e -> TimeSlotDTO.builder().index(e.getKey()).time(e.getValue()).build())
                .collect(toList());
        return AvailabilitySearchResultsDTO.builder().day(entry.getKey()).timeSlots(timeSlotDTOS).build();
    }
}
