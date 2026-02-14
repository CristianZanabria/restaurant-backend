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
@Table(name = "receta")
public class Recipe {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_receta")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_plato", nullable = false, foreignKey = @ForeignKey(name = "FK_USER_ROLE"))
    private Dish dish;

    @ManyToOne
    @JoinColumn(name = "id_insumo", nullable = false, foreignKey = @ForeignKey(name = "FK_USER_ROLE"))
    private Supply supply;

    @Column(name = "cantidad", length = 50, nullable = false)
    private short quantity;

}
