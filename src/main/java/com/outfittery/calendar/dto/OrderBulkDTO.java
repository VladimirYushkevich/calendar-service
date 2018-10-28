package com.outfittery.calendar.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderBulkDTO {
    @NotNull
    @ApiModelProperty(notes = "Start date", required = true)
    private Date start;
    @NotNull
    @ApiModelProperty(notes = "End date", required = true)
    private Date end;
    @NotNull
    @ApiModelProperty(notes = "Ids for customers", required = true)
    private Set<Long> customerIds;
}
