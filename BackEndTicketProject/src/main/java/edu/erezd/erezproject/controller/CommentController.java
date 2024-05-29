package edu.erezd.erezproject.controller;

import edu.erezd.erezproject.dto.comment.CommentRequestDTO;
import edu.erezd.erezproject.dto.comment.CommentResponseDTO;
import edu.erezd.erezproject.dto.error.ErrorDTO;
import edu.erezd.erezproject.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1")
@SecurityRequirement(name = "bearer Authentication")
@Tag(name = "CommentController",
        description = "API for managing comments on tickets. Provides functionality to add, update, and delete comments.")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/tickets/{id}/comments")
    @Operation(summary = "Create a Comment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comment Created Successfully", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input, comment cannot be created", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized, JWT token is missing or invalid", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class))}),
            @ApiResponse(responseCode = "403", description = "Forbidden, user does not have permission", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Ticket not found", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class))})
    })
    public ResponseEntity<CommentResponseDTO> createComment(
            Authentication authentication,
            @PathVariable(name = "id") long id,
            @Valid @RequestBody CommentRequestDTO dto,
            UriComponentsBuilder uriBuilder) {
        var saved = commentService.createComment(id, dto, authentication);

        var uri = uriBuilder
                .path("/tickets/{id}/comments")
                .buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @PutMapping("/tickets/{id}/comments/{commentId}")
    @Operation(summary = "Update a Comment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment updated successfully", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input, comment cannot be updated", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized, JWT token is missing or invalid", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class))}),
            @ApiResponse(responseCode = "403", description = "Forbidden, user does not have permission", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Comment not found", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class))})
    })
    public ResponseEntity<CommentResponseDTO> updateComment(
            Authentication authentication,
            @PathVariable("commentId") long commentId, // Ensuring path variable clarity
            @Valid @RequestBody CommentRequestDTO dto
    ) {
        var updatedComment = commentService.updateCommentById(commentId, dto, authentication);
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/tickets/{id}/comments/{commentId}")
    @Operation(summary = "Delete a Comment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Comment deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request, comment cannot be deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized, JWT token is missing or invalid",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class))}),
            @ApiResponse(responseCode = "403", description = "Forbidden, user does not have permission",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Comment not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class))})
    })
    public ResponseEntity<Void> deleteComment(
            @PathVariable("commentId") long commentId,
            Authentication authentication
    ) {
        commentService.deleteCommentById(commentId, authentication);
        return ResponseEntity.noContent().build();
    }

}
