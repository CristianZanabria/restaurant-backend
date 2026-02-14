package com.restaurant.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.restaurant.model.Supply;
import com.restaurant.model.Waste;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InventoryDTO {


    private Integer idInventary;

    private LocalDateTime fecha;

    // referencia al insumo
    private Integer supplyId;
    private String supplyName;

    private BigDecimal stockInicial;
    private BigDecimal stockFinal;

}
