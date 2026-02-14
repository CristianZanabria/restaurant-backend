package com.restaurant.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SupplyDTO {

    private Integer idSupplyDto;
    @NotNull
    private Integer idCategory;
    @NotNull
    @NotEmpty
    private String nameSupply;
    @NotNull
    @NotEmpty
    private String unitSupply;
    @NotNull
    private Integer usefulLiSupply ;

}
