package com.outfittery.calendar.dto;

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
public class StylistAvailabilitySearch {
    @NotNull
    @ApiModelProperty(notes = "Start date", required = true)
    private Date start;
    @NotNull
    @ApiModelProperty(notes = "End date", required = true)
    private Date end;
}
