package com.powalteam.szopapka.web.service;


import com.powalteam.szopapka.web.api.dto.ShoppingDTO;
import com.powalteam.szopapka.web.api.dto.ShoppingViewDTO;

import java.util.List;

public interface ShoppingService {
    public ShoppingDTO createShoppingList(ShoppingDTO shoppingDTO);

    void deleteShopping(Long id);

    List<ShoppingViewDTO> getShoppingByFamily(Long idFamily);

    ShoppingDTO updateShoppingStatus(Long id, String status);
}
