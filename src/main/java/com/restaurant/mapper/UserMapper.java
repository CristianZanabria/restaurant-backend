package com.restaurant.mapper;

import com.restaurant.config.MapperCentralConfig;
import com.restaurant.dto.UserDTO;
import com.restaurant.model.Role;
import com.restaurant.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperCentralConfig.class)
public interface UserMapper {


    @Mapping(source = "id", target = "idUser")
    @Mapping(source = "role", target = "role")
    @Mapping(source = "name", target = "userName")
    @Mapping(source = "email", target = "emailUser")
    @Mapping(source = "password", target = "passwordUser")
    UserDTO toDTO(User entity);

    @Mapping(source = "idUser", target = "id")
    @Mapping(source = "role", target = "role")
    @Mapping(source = "userName", target = "name")
    @Mapping(source = "emailUser", target = "email")
    @Mapping(source = "passwordUser", target = "password")
    User toEntity(UserDTO dto);

    //  conversión automática FK
    default Role map(Integer id) {
        if (id == null) return null;
        Role c = new Role();
        c.setId(id);
        return c;
    }
}
