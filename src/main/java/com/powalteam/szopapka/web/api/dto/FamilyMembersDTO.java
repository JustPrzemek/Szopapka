package com.powalteam.szopapka.web.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamilyMembersDTO {

    private String familyName;

    private String image;

    private List<MembersDTO> members;

    private String familyCode;
}