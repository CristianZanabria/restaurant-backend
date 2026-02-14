package com.restaurant.service.impl;


import com.restaurant.dto.IProcedureDTO;
import com.restaurant.dto.ProcedureDTO;
import com.restaurant.dto.SaleDTO;
import com.restaurant.mapper.SaleMapper;
import com.restaurant.model.*;

import com.restaurant.repo.*;
import com.restaurant.service.ISaleService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl extends CRUDImpl<Sale, Integer>implements ISaleService {
    private final ISaleRepo repo;
    private final SaleMapper mapper;

    private final IRecipeRepo recipeRepo;
    private final IInventoryRepo inventoryRepo;

    private final EntityManager em;

    @Override
    protected IGenericRepo<Sale, Integer> getRepo() {
        return repo;
    }

    @Override
    @Transactional
    public Sale registerSale(SaleDTO dto) {

        Sale sale = mapper.toEntity(dto);

        // 1) Fecha automática
        if (sale.getDateTime() == null) {
            sale.setDateTime(LocalDateTime.now());
        }

        // 2) Validación mínima
        if (sale.getDetails() == null || sale.getDetails().isEmpty()) {
            throw new RuntimeException("La venta debe tener al menos 1 detalle");
        }

        // 3) Referencias JPA (evitar transient)
        sale.setClient(em.getReference(Client.class, dto.getIdClient()));
        sale.setUser(em.getReference(User.class, dto.getIdUser()));

        // 4) Calcular totales y setear unitPrice en detalle
        BigDecimal subtotal = BigDecimal.ZERO;

        for (SaleDetail d : sale.getDetails()) {

            d.setSale(sale);

            Integer idDish = d.getDish().getId();
            Dish dish = em.getReference(Dish.class, idDish);
            d.setDish(dish);

            if (d.getQuantity() == null || d.getQuantity() <= 0) {
                throw new RuntimeException("Cantidad inválida para plato: " + idDish);
            }

            BigDecimal unitPrice = dish.getPrice(); // BigDecimal en Dish
            if (unitPrice == null || unitPrice.compareTo(BigDecimal.ZERO) <= 0) {
                throw new RuntimeException("Precio inválido para plato: " + idDish);
            }

            // snapshot del precio
            d.setUnitPrice(unitPrice);

            subtotal = subtotal.add(unitPrice.multiply(BigDecimal.valueOf(d.getQuantity())));
        }
        subtotal = subtotal.setScale(2, RoundingMode.HALF_UP);

        BigDecimal taxRate = new BigDecimal("0.18");

        BigDecimal tax = subtotal
                .multiply(taxRate)
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal total = subtotal
                .add(tax)
                .setScale(2, RoundingMode.HALF_UP);


        sale.setSubtotal(subtotal);
        sale.setTax(tax);
        sale.setTotal(total);

        // 5) Guardar venta (cascade guarda detalles)
        Sale savedSale = repo.save(sale);

        // 6) Inventario: descontar insumos según recetas por plato vendido
        LocalDate saleDate = savedSale.getDateTime().toLocalDate();

        for (SaleDetail detail : savedSale.getDetails()) {

            List<Recipe> recipes = recipeRepo.findByDish_Id(detail.getDish().getId());

            for (Recipe recipe : recipes) {

                Supply supply = recipe.getSupply();

                // consumo = cantidad en receta * cantidad platos vendidos
                BigDecimal consumo = BigDecimal.valueOf(recipe.getQuantity())
                        .multiply(BigDecimal.valueOf(detail.getQuantity()))
                        .setScale(2, RoundingMode.HALF_UP);

                Inventory inv = inventoryRepo
                        .findBySupply_IdSupplyAndFechaBetween(
                                supply.getIdSupply(),
                                saleDate.atStartOfDay(),
                                saleDate.plusDays(1).atStartOfDay()
                        )
                        .orElseGet(() -> {

                            // Buscar el último inventario antes de hoy
                            Inventory last = inventoryRepo
                                    .findTopBySupply_IdSupplyAndFechaLessThanOrderByFechaDesc(
                                            supply.getIdSupply(),
                                            saleDate.atStartOfDay()
                                    )
                                    .orElse(null);

                            BigDecimal stockBase = (last != null) ? last.getStockFinal() : BigDecimal.ZERO;

                            Inventory nuevo = new Inventory();
                            nuevo.setSupply(em.getReference(Supply.class, supply.getIdSupply()));
                            nuevo.setFecha(saleDate.atStartOfDay());
                            nuevo.setStockInicial(stockBase.setScale(2, RoundingMode.HALF_UP));
                            nuevo.setStockFinal(stockBase.setScale(2, RoundingMode.HALF_UP));

                            return inventoryRepo.save(nuevo);
                        });

                BigDecimal disponible = inv.getStockFinal();

                if (disponible.compareTo(consumo) < 0) {
                    throw new RuntimeException("Stock insuficiente de " + supply.getName());
                }

                inv.setStockFinal(disponible.subtract(consumo).setScale(2, RoundingMode.HALF_UP));
                inventoryRepo.save(inv);
            }
        }

        return savedSale;
    }
    @Override
    @Transactional
    public void deleteSale(Integer idSale) {

        Sale sale = repo.findById(idSale)
                .orElseThrow(() -> new RuntimeException("Venta no existe: " + idSale));

        LocalDate saleDate = sale.getDateTime().toLocalDate();

        for (SaleDetail detail : sale.getDetails()) {

            List<Recipe> recipes = recipeRepo.findByDish_Id(detail.getDish().getId());

            for (Recipe recipe : recipes) {

                Supply supply = recipe.getSupply();

                BigDecimal devolver = BigDecimal.valueOf(recipe.getQuantity())
                        .multiply(BigDecimal.valueOf(detail.getQuantity()));

                Inventory inv = inventoryRepo
                        .findBySupply_IdSupplyAndFechaBetween(
                                supply.getIdSupply(),
                                saleDate.atStartOfDay(),
                                saleDate.plusDays(1).atStartOfDay()
                        )
                        .orElseThrow(() -> new RuntimeException(
                                "No existe inventario del día para devolver stock. Insumo: " + supply.getName()
                        ));

                inv.setStockFinal(inv.getStockFinal().add(devolver));
                inventoryRepo.save(inv);
            }
        }

        repo.delete(sale); // cascade borra detalles
    }

    // =========================
    // CONSULTAS
    // =========================

    @Override
    public List<Sale> findByClient(Integer idClient) {
        return repo.findByClient_Id(idClient);
    }

    @Override
    public List<Sale> findByUser(Integer idUser) {
        return repo.findByUser_Id(idUser);
    }

    @Override
    public List<Sale> findByDateTimeBetween(LocalDateTime start, LocalDateTime end) {
        return repo.findByDateTimeBetween(start, end);
    }

    @Override
    public List<Sale> findByClientAndDateTimeBetween(Integer idClient, LocalDateTime start, LocalDateTime end) {
        return repo.findByClient_IdAndDateTimeBetween(idClient, start, end);
    }

    @Override
    public BigDecimal sumTotalBetween(LocalDateTime start, LocalDateTime end) {
        return repo.sumTotalBetween(start, end);
    }
}