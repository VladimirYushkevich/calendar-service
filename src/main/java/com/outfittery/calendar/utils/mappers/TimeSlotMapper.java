package com.outfittery.calendar.utils.mappers;

import com.outfittery.calendar.dto.TimeSlotDTO;
import com.outfittery.calendar.models.TimeSlot;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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

//    public static PageDTO<StylistDTO> buildPageStylistDTO(Page<Stylist> StylistPage) {
//        final List<StylistDTO> entries = StylistPage.getContent().parallelStream()
//                .map(StylistMapper::buildStylistDTO)
//                .collect(toList());
//
//        return PageDTO.<StylistDTO>builder()
//                .totalPages(StylistPage.getTotalPages())
//                .totalEntries(StylistPage.getTotalElements())
//                .entries(entries)
//                .build();
//    }
//
//    public static String[] getNullPropertyNames(Object source) {
//        final BeanWrapper src = new BeanWrapperImpl(source);
//        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
//
//        Set<String> emptyNames = new HashSet<>();
//        for (java.beans.PropertyDescriptor pd : pds) {
//            Object srcValue = src.getPropertyValue(pd.getName());
//            if (srcValue == null) emptyNames.add(pd.getName());
//        }
//        String[] result = new String[emptyNames.size()];
//        return emptyNames.toArray(result);
//    }
}
