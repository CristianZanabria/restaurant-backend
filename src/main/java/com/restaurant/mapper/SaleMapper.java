package com.restaurant.mapper;

import com.restaurant.config.MapperCentralConfig;
import com.restaurant.dto.SaleDTO;
import com.restaurant.dto.SaleDetailDTO;
import com.restaurant.model.*;
import org.mapstruct.*;

@Mapper(config = MapperCentralConfig.class)
public interface SaleMapper {

    // ====== SALE ======

    @Mapping(target = "id", source = "idSale")
    @Mapping(target = "client", source = "idClient", qualifiedByName = "clientFromId")
    @Mapping(target = "user", source = "idUser", qualifiedByName = "userFromId")
    Sale toEntity(SaleDTO dto);

    @Mapping(target = "idSale", source = "id")
    @Mapping(target = "idClient", source = "client.id")
    @Mapping(target = "idUser", source = "user.id")
    SaleDTO toDto(Sale entity);

    // ====== SALE DETAIL ======

    @Mapping(target = "id", source = "idSaleDetail")
    @Mapping(target = "sale", ignore = true) // lo seteas en el service: d.setSale(sale)
    @Mapping(target = "dish", source = "idDish", qualifiedByName = "dishFromId")
    SaleDetail toEntity(SaleDetailDTO dto);

    @Mapping(target = "idSaleDetail", source = "id")
    @Mapping(target = "idDish", source = "dish.id")
    SaleDetailDTO toDto(SaleDetail entity);

    // ====== HELPERS ======

    @Named("clientFromId")
    default Client clientFromId(Integer id) {
        if (id == null) return null;
        Client c = new Client();
        c.setId(id);
        return c;
    }

    @Named("userFromId")
    default User userFromId(Integer id) {
        if (id == null) return null;
        User u = new User();
        u.setId(id);
        return u;
    }

    @Named("dishFromId")
    default Dish dishFromId(Integer id) {
        if (id == null) return null;
        Dish d = new Dish();
        d.setId(id);
        return d;
    }
}
