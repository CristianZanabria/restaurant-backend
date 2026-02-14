package com.restaurant.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WasteDTO {


    private Integer idWaste;
    @NotNull
    @NotEmpty
    private LocalDateTime dateWaste;

    @NotNull

    private Integer idSupplyWaste;

    @NotNull
    @NotEmpty
    private short quantityWaste;

    @NotNull
    @NotEmpty
    private String reasonWaste;

}
