package com.restaurant.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "merma")
public class Waste {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_merma")
    private Integer id;
    @Column(name = "fecha",nullable = false)
    private LocalDateTime date;

    @ManyToOne//(FK)
    @JoinColumn(name = "id_insumo", nullable = false, foreignKey = @ForeignKey(name = "FK_SUPPLY_CATEGORY"))
    private Supply supply;

    @Column(name = "cantidad", length = 50, nullable = false)
    private short quantity;

    @Column(name = "motivo", length = 150, nullable = false)
    private String reason;

}
