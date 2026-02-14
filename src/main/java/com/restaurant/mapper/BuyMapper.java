package com.restaurant.mapper;

import com.restaurant.config.MapperCentralConfig;
import com.restaurant.dto.BuyDTO;
import com.restaurant.dto.BuyDetailDTO;
import com.restaurant.model.*;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(config = MapperCentralConfig.class)
public interface BuyMapper {
    // ========== ENTITY -> DTO ==========

    @Mapping(target = "idBuy", source = "id")
    @Mapping(target = "dateTime", source = "dateTime")
    @Mapping(target = "idProvider", source = "provider.id") // ajusta si tu PK se llama distinto
    @Mapping(target = "idUser", source = "user.id")         // ajusta si tu PK se llama distinto
    @Mapping(target = "details", source = "details")
    BuyDTO toDto(Buy entity);

    List<BuyDTO> toDtoList(List<Buy> entities);

    @Mapping(target = "idBuyDetail", source = "id")
    @Mapping(target = "idSupply", source = "supply.idSupply")     // ajusta si tu PK se llama distinto
    BuyDetailDTO toDto(BuyDetail entity);

    List<BuyDetailDTO> toDetailDtoList(List<BuyDetail> entities);

    // ========== DTO -> ENTITY ==========

    @Mapping(target = "id", source = "idBuy")
    @Mapping(target = "provider", expression = "java(providerFromId(dto.getIdProvider()))")
    @Mapping(target = "user", expression = "java(userFromId(dto.getIdUser()))")
    @Mapping(target = "details", source = "details")
    Buy toEntity(BuyDTO dto);

    @Mapping(target = "id", source = "idBuyDetail")
    @Mapping(target = "buy", ignore = true) // se setea en el service: d.setBuy(buy)
    @Mapping(target = "supply", expression = "java(supplyFromId(dto.getIdSupply()))")
    BuyDetail toEntity(BuyDetailDTO dto);

    List<BuyDetail> toDetailEntityList(List<BuyDetailDTO> dtos);

    // ========== HELPERS (crear entidades con solo ID) ==========
    // OJO: ajusta nombres de ID si no son "id"

    default Provider providerFromId(Integer id) {
        if (id == null) return null;
        Provider p = new Provider();
        p.setId(id);
        return p;
    }

    default User userFromId(Integer id) {
        if (id == null) return null;
        User u = new User();
        u.setId(id);
        return u;
    }

    default Supply supplyFromId(Integer id) {
        if (id == null) return null;
        Supply s = new Supply();
        s.setIdSupply(id);
        return s;
    }
}
