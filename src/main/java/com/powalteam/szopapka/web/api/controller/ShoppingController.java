package com.powalteam.szopapka.web.api.controller;


import com.powalteam.szopapka.web.api.dto.ShoppingDTO;
import com.powalteam.szopapka.web.api.dto.ShoppingViewDTO;
import com.powalteam.szopapka.web.model.Shopping;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/shopping")
@Tag(name = "Shopping", description = "Endpoint for shopping")
public interface ShoppingController {

    @PostMapping("/createListShopping")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Add new List",
            description = "Add new List to database. Status is default NEW"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Shopping.class)
                    )
            )
    })
    ShoppingDTO createListShopping(@RequestBody ShoppingDTO shoppingDTO);

    @DeleteMapping("/deleteShopping/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Delete shopping item",
            description = "Deletes a shopping item by its ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Shopping item not found")
    })
    void deleteShopping(@PathVariable Long id);

    @PutMapping("/updateStatus/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Update shopping status",
            description = "Updates the status of a shopping item"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Status updated successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Shopping.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Shopping item not found")
    })
    ShoppingDTO updateShoppingStatus(@PathVariable Long id, @RequestParam String status);

    @GetMapping("/getByFamily/{idFamily}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get all shopping lists for a family",
            description = "Returns all shopping list items for a given family"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ShoppingViewDTO.class)))
    })
    List<ShoppingViewDTO> getShoppingByFamily(@PathVariable Long idFamily);

}
