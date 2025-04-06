package com.powalteam.szopapka.web.service;


import com.powalteam.szopapka.web.api.dto.ShoppingDTO;

public interface ShoppingService {
    public ShoppingDTO createShoppingList(ShoppingDTO shoppingDTO);

    void deleteShopping(Long id);

    ShoppingDTO updateShoppingStatus(Long id, String status);
}
