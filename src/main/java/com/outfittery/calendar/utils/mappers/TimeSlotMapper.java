package com.outfittery.calendar.utils.mappers;

import com.outfittery.calendar.dto.TimeSlotDTO;
import com.outfittery.calendar.models.TimeSlot;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * Utility class for mapping time slot to related DTOs and vice versa.
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TimeSlotMapper {

    public static TimeSlot buildTimeSlot(TimeSlotDTO dto) {
        TimeSlot timeSlot = new TimeSlot();

        copyProperties(dto, timeSlot);

        return timeSlot;
    }

    public static TimeSlotDTO buildTimeSlotDTO(TimeSlot timeSlot) {
        final TimeSlotDTO dto = new TimeSlotDTO();

        copyProperties(timeSlot, dto);

        return dto;
    }

    public static TimeSlotDTO buildTimeSlotDTOFromEntry(Map.Entry<Date, String> entry) {
        return TimeSlotDTO.builder().day(entry.getKey()).availability(entry.getValue()).build();
    }
}
