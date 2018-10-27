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
public class TimeSlotDTO {
    private Long id;
    @NotNull
    @ApiModelProperty(notes = "Day when stylist is available", required = true)
    private Date day;
    @NotNull
    @ApiModelProperty(notes = "Encoded availability string")
    private String availability = "0000000000000000";
    @NotNull
    @ApiModelProperty(notes = "Stylist id", required = true)
    private Long stylistId;
}
