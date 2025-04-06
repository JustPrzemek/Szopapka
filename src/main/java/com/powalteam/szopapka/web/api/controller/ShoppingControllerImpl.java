package com.powalteam.szopapka.web.api.controller;

import com.powalteam.szopapka.web.api.dto.ShoppingDTO;
import com.powalteam.szopapka.web.api.dto.ShoppingViewDTO;
import com.powalteam.szopapka.web.model.Shopping;
import com.powalteam.szopapka.web.service.ShoppingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ShoppingControllerImpl implements ShoppingController {

    private final ShoppingServiceImpl shoppingService;

    @Override
    public ShoppingDTO createListShopping(@RequestBody ShoppingDTO shoppingDTO)
    {
        return shoppingService.createShoppingList(shoppingDTO);
    }

    @Override
    public List<ShoppingViewDTO> getShoppingByFamily(Long idFamily) {
        return shoppingService.getShoppingByFamily(idFamily);
    }

    @Override
    public void deleteShopping(@PathVariable Long id) {
        shoppingService.deleteShopping(id);
    }

    @Override
    public ShoppingDTO updateShoppingStatus(@PathVariable Long id, @RequestParam String status) {
        return shoppingService.updateShoppingStatus(id, status);
    }
}
