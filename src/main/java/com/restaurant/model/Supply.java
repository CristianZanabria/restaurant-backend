package com.restaurant.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "insumo")
public class Supply {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_insumo")
    private Integer idSupply;

    @ManyToOne//(FK)
    @JoinColumn(name = "id_categoria_insumo", nullable = false, foreignKey = @ForeignKey(name = "FK_SUPPLY_CATEGORY"))
    private CategorySupply categorySupply;

    @Column(name = "nombre", length = 50, nullable = false)
    private String name;

    @Column(name = "unidad", length = 150, nullable = false)
    private String unit;

    @Column(name = "vida_util_dias", columnDefinition = "decimal(6,2)", nullable = false)
    private Integer usefulLive ;



}
