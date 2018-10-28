package com.outfittery.calendar.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AvailabilitySearchResultsDTO {
    @ApiModelProperty(notes = "Day when stylist is available", required = true)
    private Date day;
    @ApiModelProperty(notes = "List of available time slots", required = true)
    private List<TimeSlotDTO> timeSlots;
}
