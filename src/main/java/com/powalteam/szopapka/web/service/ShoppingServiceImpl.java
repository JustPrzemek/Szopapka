package com.powalteam.szopapka.web.service;

import com.powalteam.szopapka.web.api.dto.ShoppingDTO;
import com.powalteam.szopapka.web.api.dto.ShoppingViewDTO;
import com.powalteam.szopapka.web.api.mapper.ShoppingMapper;
import com.powalteam.szopapka.web.api.mapper.ShoppingViewMapper;
import com.powalteam.szopapka.web.model.ShoppingView;
import com.powalteam.szopapka.web.model.User;
import com.powalteam.szopapka.web.repository.ShoppingRepository;
import com.powalteam.szopapka.web.repository.ShoppingViewRepository;
import com.powalteam.szopapka.web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.powalteam.szopapka.web.model.Shopping;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShoppingServiceImpl implements ShoppingService {

    private final UserRepository userRepository;

    private final ShoppingRepository shoppingRepository;

    @Autowired
    private ShoppingViewRepository shoppingViewRepository;

    private final ShoppingMapper shoppingMapper;

    private final ShoppingViewMapper shoppingViewMapper;

    public ShoppingDTO createShoppingList(ShoppingDTO shoppingDTO) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userEmail = authentication.getName();

            User user = userRepository.findByEmail(userEmail)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Shopping shopping = new Shopping();
            shopping.setIdUser(user.getId());
            shopping.setIdFamily(shoppingDTO.getIdFamily());
            shopping.setContent(shoppingDTO.getContent());
            shopping.setQuantity(shoppingDTO.getQuantity());
            shopping.setStatus("NEW");

            Shopping saved = shoppingRepository.save(shopping);
            return shoppingMapper.toDto(saved);

        } catch (Exception e) {
            throw new RuntimeException("Error creating shopping list: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteShopping(Long id) {
        if (!shoppingRepository.existsById(id)) {
            throw new RuntimeException("Shopping item not found");
        }
        shoppingRepository.deleteById(id);
    }

    @Override
    public ShoppingDTO updateShoppingStatus(Long id, String status) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userEmail = authentication.getName();

            User user = userRepository.findByEmail(userEmail)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Shopping shopping = shoppingRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Shopping item not found"));

            if (!shopping.getIdUser().equals(user.getId())) {
                throw new RuntimeException("User not authorized to update this shopping item");
            }

            shopping.setStatus(status);
            shopping.setIdUser(user.getId());
            Shopping updated = shoppingRepository.save(shopping);
            return shoppingMapper.toDto(updated);

        } catch (Exception e) {
            throw new RuntimeException("Error updating shopping status: " + e.getMessage(), e);
        }

    }
    @Override
    public List<ShoppingViewDTO> getShoppingByFamily(Long idFamily) {
        List<ShoppingView> viewList = shoppingViewRepository.findByIdFamily(idFamily);
        return viewList.stream()
                .map(shoppingViewMapper::toShoppingViewDTO)
                .collect(Collectors.toList());
    }

}


