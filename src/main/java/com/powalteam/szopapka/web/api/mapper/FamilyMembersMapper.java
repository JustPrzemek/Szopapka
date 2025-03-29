package com.powalteam.szopapka.web.api.mapper;

import com.powalteam.szopapka.web.api.dto.FamilyMembersDTO;
import com.powalteam.szopapka.web.model.FamilyMembersView;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface FamilyMembersMapper {
    List<FamilyMembersDTO> toDtoList(List<FamilyMembersView> views);
}
