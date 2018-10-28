package com.outfittery.calendar.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AvailabilityDTO {
    private Long id;
    @NotNull
    @ApiModelProperty(notes = "Day when stylist is available", required = true)
    private Date day;
    @NotNull
    @ApiModelProperty(notes = "Encoded time slots as string")
    private String encodedTimeSlots = "0000000000000000";
    @NotNull
    @ApiModelProperty(notes = "Stylist id", required = true)
    private Long stylistId;
}
