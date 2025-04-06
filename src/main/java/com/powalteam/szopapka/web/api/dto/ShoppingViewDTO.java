package com.powalteam.szopapka.web.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingViewDTO {
    private Long id;
    private Long idFamily;
    private String status;
    private String content;
    private Integer quantity;
    private Long idUser;
    private String userName;
    private String familyName;
}
