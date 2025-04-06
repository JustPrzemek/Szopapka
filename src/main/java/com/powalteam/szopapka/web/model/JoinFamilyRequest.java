package com.powalteam.szopapka.web.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class JoinFamilyRequest {
    @NotBlank
    private String familyCode;
}
