package com.restaurant.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaleDetailDTO {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Integer idSaleDetail;

    @NotNull
    private Integer idDish;

    @NotNull
    @Min(1)
    private Integer quantity;

    //  el backend toma el precio desde Dish
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private BigDecimal unitPrice;

}
