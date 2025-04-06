package com.powalteam.szopapka.web.api.mapper;

import com.powalteam.szopapka.web.api.dto.ShoppingViewDTO;
import com.powalteam.szopapka.web.model.ShoppingView;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShoppingViewMapper {
    ShoppingViewDTO toDto(ShoppingView shoppingView);
    List<ShoppingViewDTO> toDtoList(List<ShoppingView> shoppingViews);
}
