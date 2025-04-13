package com.powalteam.szopapka.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import com.powalteam.szopapka.web.api.dto.ShoppingDTO;
import com.powalteam.szopapka.web.api.dto.ShoppingViewDTO;
import com.powalteam.szopapka.web.api.mapper.ShoppingMapper;
import com.powalteam.szopapka.web.api.mapper.ShoppingViewMapper;
import com.powalteam.szopapka.web.model.Shopping;
import com.powalteam.szopapka.web.model.ShoppingView;
import com.powalteam.szopapka.web.model.User;
import com.powalteam.szopapka.web.repository.ShoppingRepository;
import com.powalteam.szopapka.web.repository.ShoppingViewRepository;
import com.powalteam.szopapka.web.repository.UserRepository;
import com.powalteam.szopapka.web.service.ShoppingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
class ShoppingServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ShoppingRepository shoppingRepository;

    @Mock
    private ShoppingViewRepository shoppingViewRepository;

    @Mock
    private ShoppingMapper shoppingMapper;

    @Mock
    private ShoppingViewMapper shoppingViewMapper;

    @InjectMocks
    private ShoppingServiceImpl shoppingService;

    private final String testUserEmail = "test@example.com";
    private final Long testUserId = 1L;
    private final Long testFamilyId = 1L;
    private final Long testShoppingId = 1L;
    private final String testStatus = "DONE";

    @BeforeEach
    void setUp() {
        // Mock security context
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn(testUserEmail);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void createShoppingList_ShouldCreateNewShoppingList() {
        // Arrange
        ShoppingDTO shoppingDTO = new ShoppingDTO();
        shoppingDTO.setIdFamily(testFamilyId);
        shoppingDTO.setContent("Test content");
        shoppingDTO.setQuantity(2);

        User user = new User();
        user.setId(testUserId);
        user.setEmail(testUserEmail);

        Shopping shopping = new Shopping();
        shopping.setIdUser(testUserId);
        shopping.setIdFamily(testFamilyId);
        shopping.setContent("Test content");
        shopping.setQuantity(2);
        shopping.setStatus("NEW");

        Shopping savedShopping = new Shopping();
        savedShopping.setId(testShoppingId);
        savedShopping.setIdUser(testUserId);
        savedShopping.setIdFamily(testFamilyId);
        savedShopping.setContent("Test content");
        savedShopping.setQuantity(2);
        savedShopping.setStatus("NEW");

        ShoppingDTO expectedDto = new ShoppingDTO();
        expectedDto.setIdFamily(testFamilyId);
        expectedDto.setContent("Test content");
        expectedDto.setQuantity(2);

        when(userRepository.findByEmail(testUserEmail)).thenReturn(Optional.of(user));
        when(shoppingRepository.save(any(Shopping.class))).thenReturn(savedShopping);
        when(shoppingMapper.toDto(savedShopping)).thenReturn(expectedDto);

        // Act
        ShoppingDTO result = shoppingService.createShoppingList(shoppingDTO);

        // Assert
        assertNotNull(result);
        assertEquals(testFamilyId, result.getIdFamily());
        assertEquals("Test content", result.getContent());

        verify(shoppingRepository, times(1)).save(any(Shopping.class));
    }

    @Test
    void createShoppingList_UserNotFound_ShouldThrowException() {
        // Arrange
        ShoppingDTO shoppingDTO = new ShoppingDTO();
        when(userRepository.findByEmail(testUserEmail)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> shoppingService.createShoppingList(shoppingDTO));
    }

    @Test
    void updateShoppingStatus_ShouldUpdateStatus() {
        // Arrange
        User user = new User();
        user.setId(testUserId);
        user.setEmail(testUserEmail);

        Shopping shopping = new Shopping();
        shopping.setId(testShoppingId);
        shopping.setIdUser(testUserId);
        shopping.setStatus("NEW");

        Shopping updatedShopping = new Shopping();
        updatedShopping.setId(testShoppingId);
        updatedShopping.setIdUser(testUserId);
        updatedShopping.setStatus(testStatus);

        ShoppingDTO expectedDto = new ShoppingDTO();
        expectedDto.setIdFamily(testFamilyId);
        expectedDto.setContent("Test content");

        when(userRepository.findByEmail(testUserEmail)).thenReturn(Optional.of(user));
        when(shoppingRepository.findById(testShoppingId)).thenReturn(Optional.of(shopping));
        when(shoppingRepository.save(any(Shopping.class))).thenReturn(updatedShopping);
        when(shoppingMapper.toDto(updatedShopping)).thenReturn(expectedDto);

        // Act
        ShoppingDTO result = shoppingService.updateShoppingStatus(testShoppingId, testStatus);

        // Assert
        assertNotNull(result);
        assertEquals(testFamilyId, result.getIdFamily());
        verify(shoppingRepository, times(1)).save(any(Shopping.class));
    }

    @Test
    void updateShoppingStatus_UserNotAuthorized_ShouldThrowException() {
        // Arrange
        User user = new User();
        user.setId(testUserId);
        user.setEmail(testUserEmail);

        Shopping shopping = new Shopping();
        shopping.setId(testShoppingId);
        shopping.setIdUser(999L); // Different user ID

        when(userRepository.findByEmail(testUserEmail)).thenReturn(Optional.of(user));
        when(shoppingRepository.findById(testShoppingId)).thenReturn(Optional.of(shopping));

        // Act & Assert
        assertThrows(RuntimeException.class, () ->
                shoppingService.updateShoppingStatus(testShoppingId, testStatus));
    }

    @Test
    void updateShoppingStatus_ItemNotFound_ShouldThrowException() {
        // Arrange
        User user = new User();
        user.setId(testUserId);
        user.setEmail(testUserEmail);

        when(userRepository.findByEmail(testUserEmail)).thenReturn(Optional.of(user));
        when(shoppingRepository.findById(testShoppingId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () ->
                shoppingService.updateShoppingStatus(testShoppingId, testStatus));
    }
}
