package com.restaurant.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "inventario")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inventario")
    private Integer id;

    @Column( nullable = false)
    private LocalDateTime fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "id_insumo",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_INVENTARIO_INSUMO")
    )
    private Supply supply;

    @Column(name = "stock_inicial", nullable = false, precision = 8, scale = 2)
    private BigDecimal stockInicial;

    @Column(name = "stock_final", nullable = false, precision = 8, scale = 2)
    private BigDecimal stockFinal;

}
