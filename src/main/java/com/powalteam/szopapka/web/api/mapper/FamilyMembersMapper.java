package com.powalteam.szopapka.web.api.mapper;

import com.powalteam.szopapka.web.api.dto.MembersDTO;
import com.powalteam.szopapka.web.api.dto.UserDTO;
import com.powalteam.szopapka.web.model.FamilyMembersView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
@Component
public interface FamilyMembersMapper {
    @Mapping(source = "mail", target = "mail")
    MembersDTO toUserDto(FamilyMembersView view);

    default List<MembersDTO> toMemberDtoList(List<FamilyMembersView> views) {
        return views.stream()
                .map(this::toUserDto)
                .collect(Collectors.toList());
    }
}