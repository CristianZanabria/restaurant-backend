package com.restaurant.dto;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
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
public class BuyDetailDTO {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Integer idBuyDetail;

    @NotNull
    private Integer idSupply;      // id_insumo

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal quantity;       // cantidad

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal  unitPrice;      // precio_unitario
}
