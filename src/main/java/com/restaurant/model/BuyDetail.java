package com.restaurant.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "detalle_compra")
@Builder
public class BuyDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_compra")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_compra", nullable = false,
            foreignKey = @ForeignKey(name = "FK_BUY_DETAIL_BUY"))
    private Buy buy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_insumo", nullable = false,
            foreignKey = @ForeignKey(name = "FK_BUY_DETAIL_SUPPLY"))
    private Supply supply;

    @Column(name = "cantidad", nullable = false, precision = 8, scale = 2)
    private BigDecimal quantity;

    @Column(name = "precio_unitario", nullable = false, precision = 8, scale = 2)
    private BigDecimal  unitPrice;
}
