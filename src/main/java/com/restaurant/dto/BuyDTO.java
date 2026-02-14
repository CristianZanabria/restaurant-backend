package com.restaurant.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.restaurant.model.BuyDetail;
import com.restaurant.model.Provider;
import com.restaurant.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
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
public class BuyDTO {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Integer idBuy;

    // si quieres que el backend ponga now(), puedes omitirlo en el JSON
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime dateTime;

    @NotNull
    private Integer idProvider;  // id_proveedor

    @NotNull
    private Integer idUser;      // id_usuario

    // estos normalmente los calcula el backend, por eso van NULL en request
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private BigDecimal subtotal;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private BigDecimal  tax;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private BigDecimal  total;

    @Valid
    @NotNull
    @Size(min = 1)
    private List<BuyDetailDTO> details;
}
