package com.restaurant.service.impl;

import com.restaurant.dto.BuyDTO;
import com.restaurant.mapper.BuyMapper;
import com.restaurant.model.*;
import com.restaurant.repo.*;
import com.restaurant.service.IBuyService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BuyServiceImpl extends CRUDImpl<Buy, Integer>implements IBuyService {

    private final IBuyRepo repo;
    private final IBuyDetailRepo detailRepo; // opcional, no es obligatorio para registrar
    private final ISupplyRepo supplyRepo;    // opcional (solo si quieres validar existencia con findById)
    private final IInventoryRepo inventoryRepo;

    private final BuyMapper mapper;
    private final EntityManager em;

    @Override
    protected IGenericRepo<Buy, Integer> getRepo() {
        return repo;
    }

    @Override
    @Transactional
    public Buy registerBuy(BuyDTO dto) {

        Buy buy = mapper.toEntity(dto);

        // fecha: si no viene en request, se pone ahora
        if (buy.getDateTime() == null) {
            buy.setDateTime(LocalDateTime.now());
        }

        // Referencias JPA para evitar transient
        buy.setProvider(em.getReference(Provider.class, dto.getIdProvider()));
        buy.setUser(em.getReference(User.class, dto.getIdUser()));

        // Calcular totales + asegurar relaciones en detalle
        BigDecimal subtotal = BigDecimal.ZERO;

        for (BuyDetail d : buy.getDetails()) {

            d.setBuy(buy);

            Integer idSupply = d.getSupply().getIdSupply();

            // Validación opcional de existencia del insumo
            supplyRepo.findById(idSupply)
                    .orElseThrow(() -> new RuntimeException("Insumo no existe: " + idSupply));

            Supply supplyRef = em.getReference(Supply.class, idSupply);
            d.setSupply(supplyRef);

            if (d.getQuantity() == null || d.getQuantity().compareTo(BigDecimal.ZERO) <= 0) {
                throw new RuntimeException("Cantidad inválida para insumo: " + idSupply);
            }
            if (d.getUnitPrice() == null || d.getUnitPrice().compareTo(BigDecimal.ZERO) <= 0) {
                throw new RuntimeException("Precio inválido para insumo: " + idSupply);
            }

            // subtotal += quantity * unitPrice
            subtotal = subtotal.add(d.getQuantity().multiply(d.getUnitPrice()));
        }

        subtotal = subtotal.setScale(2, RoundingMode.HALF_UP);
        BigDecimal tax = subtotal.multiply(new BigDecimal("0.18")).setScale(2, RoundingMode.HALF_UP);
        BigDecimal total = subtotal.add(tax).setScale(2, RoundingMode.HALF_UP);

        buy.setSubtotal(subtotal);
        buy.setTax(tax);
        buy.setTotal(total);

        // Guardar compra (cascade guarda detalles)
        Buy savedBuy = repo.save(buy);

        // =========================
        // INVENTARIO / KARDEX (ENTRADA por COMPRA)
        // =========================
        for (BuyDetail d : savedBuy.getDetails()) {

            Integer idSupply = d.getSupply().getIdSupply();

            Inventory last = inventoryRepo
                    .findTopBySupply_IdSupplyOrderByFechaDesc(idSupply)
                    .orElse(null);

            BigDecimal stockAnterior = (last != null) ? last.getStockFinal() : BigDecimal.ZERO;

            BigDecimal entrada = d.getQuantity();          // ✅ ya es BigDecimal
            BigDecimal stockFinal = stockAnterior.add(entrada).setScale(2, RoundingMode.HALF_UP);

            Inventory inv = new Inventory();
            inv.setFecha(savedBuy.getDateTime() != null ? savedBuy.getDateTime() : LocalDateTime.now());
            inv.setSupply(em.getReference(Supply.class, idSupply));
            inv.setStockInicial(stockAnterior.setScale(2, RoundingMode.HALF_UP));
            inv.setStockFinal(stockFinal);

            inventoryRepo.save(inv);
        }

        return savedBuy;
    }

    // =========================
    // Consultas del IBuyService
    // =========================

    @Override
    public List<Buy> findByDateBetween(LocalDateTime start, LocalDateTime end) {
        return repo.findByDateTimeBetween(start, end);
    }

    @Override
    public List<Buy> findByProvider(Integer idProvider) {
        return repo.findByProvider_Id(idProvider);
    }

    @Override
    public List<Buy> findByUser(Integer idUser) {
        return repo.findByUser_Id(idUser);
    }

    @Override
    public BigDecimal sumTotalBetween(LocalDateTime start, LocalDateTime end) {
        return repo.sumTotalBetween(start, end);
}

    @Override
    public List<Buy> findByProviderAndDateTimeBetween(
            Integer idProvider,
            LocalDateTime start,
            LocalDateTime end
    ) {
        return repo.findByProvider_IdAndDateTimeBetween(idProvider, start, end);
    }

}