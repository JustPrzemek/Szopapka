package com.powalteam.szopapka.web.api.mapper;

import com.powalteam.szopapka.web.api.dto.FamilyMembersDTO;
import com.powalteam.szopapka.web.model.FamilyMembers;
import org.springframework.stereotype.Component;

@Component
public class FamilyMembersMapper {
    public FamilyMembersDTO toDTO(FamilyMembers familyMembers)
    {
        return new FamilyMembersDTO(
                familyMembers.getFamilyName(),
                familyMembers.getImage(),
                familyMembers.getMail());
    }
}
