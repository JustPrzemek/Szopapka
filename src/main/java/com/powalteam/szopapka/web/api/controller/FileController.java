package com.powalteam.szopapka.web.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/files")
@Tag(name = "Files", description = "Endpoint for retrieving images")
public interface FileController {

    @GetMapping("/{fileName}")
    @Operation(
            summary = "Get image",
            description = "Retrieves an image for frontend display"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Image retrieved successfully",
                    content = @Content(mediaType = "image/jpeg", schema = @Schema(implementation = Resource.class))
            ),
            @ApiResponse(responseCode = "404", description = "Image not found")
    })
    ResponseEntity<Resource> getImage(@PathVariable String fileName);
}