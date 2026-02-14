package com.restaurant.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaleDTO {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Integer idSale;

    // opcional: si no viene, el backend pone now()
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime dateTime;

    @NotNull
    private Integer idClient;

    @NotNull
    private Integer idUser;

    //  calculados por el backend
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private BigDecimal subtotal;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private BigDecimal tax;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private BigDecimal total;

    @Valid
    @NotNull
    @Size(min = 1)
    private List<SaleDetailDTO> details;



}
