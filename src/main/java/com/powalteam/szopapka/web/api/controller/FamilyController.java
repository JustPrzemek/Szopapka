package com.powalteam.szopapka.web.api.controller;

import com.powalteam.szopapka.web.api.dto.FamilyDTO;
import com.powalteam.szopapka.web.api.dto.FamilyMembersDTO;
import com.powalteam.szopapka.web.model.Family;
import com.powalteam.szopapka.web.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/Family")
@Tag(name = "Family", description = "Endpoint for create family")
public interface FamilyController {

    @PostMapping(value = "/createFamily", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        @ResponseStatus(HttpStatus.OK)
        @Operation(
                summary = "Add new family",
                description = "Add new family to the database"
        )
        @ApiResponses(value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "OK",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = User.class)
                        )
                )

        })
        Family createFamily(@RequestBody FamilyDTO familyDTO);

    @GetMapping(value = "/getFamily")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get family with members",
            description = "Get family information including its members"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = FamilyMembersDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Family not found"
            )
    })
    public List<FamilyMembersDTO> getFamily();
}

