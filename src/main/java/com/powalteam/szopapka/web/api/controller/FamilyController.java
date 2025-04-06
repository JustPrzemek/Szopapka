package com.powalteam.szopapka.web.api.controller;

import com.powalteam.szopapka.web.api.dto.FamilyDTO;
import com.powalteam.szopapka.web.api.dto.FamilyMembersDTO;
import com.powalteam.szopapka.web.model.Family;
import com.powalteam.szopapka.web.model.JoinFamilyRequest;
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
    @GetMapping(value = "/getFamilyWithMembers")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get family with all members",
            description = "Get complete family information including all members"
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
    FamilyMembersDTO getFamilyWithMembers();

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Join family by code",
            description = "Allows user to join existing family using family code"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully joined family"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Family not found"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request"
            )
    })
    String joinFamilyByCode(@RequestBody JoinFamilyRequest request) throws Exception;
}

