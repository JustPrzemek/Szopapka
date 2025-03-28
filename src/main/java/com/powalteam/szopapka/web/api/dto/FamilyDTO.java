package com.powalteam.szopapka.web.api.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class FamilyDTO {

    private String familyName;

    private MultipartFile image;

}
