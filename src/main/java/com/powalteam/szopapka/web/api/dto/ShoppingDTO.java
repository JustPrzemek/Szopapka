package com.powalteam.szopapka.web.api.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingDTO {
    private Long idFamily;

    private String content;

    private Integer quantity;

}
