package com.restaurant.repo;

import com.restaurant.model.Inventory;

import java.time.LocalDateTime;
import java.util.Optional;

public interface IInventoryRepo extends IGenericRepo<Inventory, Integer>{
    Optional<Inventory> findBySupply_IdSupply(Integer idSupply);
    Optional<Inventory> findBySupply_IdSupplyAndFechaBetween(
            Integer idSupply,
            LocalDateTime start,
            LocalDateTime end
    );
    Optional<Inventory> findTopBySupply_IdSupplyAndFechaLessThanOrderByFechaDesc(
            Integer idSupply,
            LocalDateTime fecha
    );
    Optional<Inventory> findTopBySupply_IdSupplyOrderByFechaDesc(Integer idSupply);

}
