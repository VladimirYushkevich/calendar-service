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
public class CustomerDTO {
    private Long id;
    @NotNull
    @ApiModelProperty(notes = "Fist name of customer", required = true)
    private String firstName;
    @NotNull
    @ApiModelProperty(notes = "Last name of customer", required = true)
    private String lastName;
}
