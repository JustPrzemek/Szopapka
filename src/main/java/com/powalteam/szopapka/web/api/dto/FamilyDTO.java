package com.powalteam.szopapka.web.api.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nullable;


@Data
public class FamilyDTO {

    private String familyName;

    @Nullable
    private MultipartFile image;

}
