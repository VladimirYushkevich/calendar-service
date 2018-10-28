package com.outfittery.calendar.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TimeSlotDTO {
    @NotNull
    @ApiModelProperty(notes = "Index of time slot in encoded availability string", required = true)
    private Integer index;
    @NotNull
    @ApiModelProperty(notes = "Time of time slot", required = true)
    private String time;
}
