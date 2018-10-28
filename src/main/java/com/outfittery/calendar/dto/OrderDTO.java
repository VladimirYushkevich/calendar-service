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
public class OrderDTO {
    private Long id;
    private Long stylistId;
    private String timeSlot;
    @NotNull
    @ApiModelProperty(notes = "Customer id", required = true)
    private Long customerId;
    @NotNull
    @ApiModelProperty(notes = "Day when stylist is available", required = true)
    private Date day;
    @NotNull
    @ApiModelProperty(notes = "Time slot index", required = true)
    private Integer timeSlotIndex;
}
