package com.powalteam.szopapka.web.api.controller;


import com.powalteam.szopapka.web.api.dto.UserDTO;
import com.powalteam.szopapka.web.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/registration")
@Tag(name = "Registration", description = "Endpoint for registration")
public interface RegistrationController {

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Add new user",
            description = "Add new user from Firebase to the database"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content(
                            mediaType = "application/json"
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            mediaType = "application/json"
                    )
            )
    })
    User registerUser(@RequestBody UserDTO userDTO);
}