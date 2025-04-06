package com.powalteam.szopapka.web.api.mapper;

import com.powalteam.szopapka.web.api.dto.ShoppingDTO;
import com.powalteam.szopapka.web.model.Shopping;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface ShoppingMapper {
    ShoppingDTO toDto(Shopping shopping);

}
