package com.powalteam.szopapka.web.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamilyMembersDTO {

    private String familyName;

    private String image;

    private String mail;
}