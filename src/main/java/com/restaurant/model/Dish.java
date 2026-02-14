package com.restaurant.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "plato")
public class Dish {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plato")
    private Integer id;

    @ManyToOne//(FK)
    @JoinColumn(name = "id_categoria", nullable = false, foreignKey = @ForeignKey(name = "FK_SUPPLY_CATEGORY"))
    private CategoryDish category;

    @Column(name = "nombre", length = 50, nullable = false)
    private String name;

    @Column(name = "precio",precision = 6, scale = 2, nullable = false)
    private BigDecimal price;

}
