package edu.erezd.erezproject.controller;

import edu.erezd.erezproject.dto.error.ErrorDTO;
import edu.erezd.erezproject.dto.ticket.TicketCloseDTO;
import edu.erezd.erezproject.dto.ticket.TicketCreateDTO;
import edu.erezd.erezproject.dto.ticket.TicketListDTO;
import edu.erezd.erezproject.dto.ticket.TicketResponseDTO;
import edu.erezd.erezproject.exception.BadRequestException;
import edu.erezd.erezproject.exception.ResourceNotFoundException;
import edu.erezd.erezproject.exception.UnauthorizedException;
import edu.erezd.erezproject.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/tickets")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "TicketController", description = "API for managing tickets. Allows for creating, updating, opening, closing, and fetching tickets.")
@RequiredArgsConstructor
public class TicketController {

    private static final Logger logger = LoggerFactory.getLogger(TicketController.class);
    private final TicketService ticketService;

    @PostMapping
    @Operation(summary = "Create a Ticket")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ticket Created Successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TicketResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request due to invalid input",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized due to missing or invalid JWT token",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden, user lacks necessary permissions",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)))
    })
    public ResponseEntity<TicketResponseDTO> createTicket(
            @RequestBody @Valid TicketCreateDTO dto,
            UriComponentsBuilder uriBuilder,
            Authentication authentication) {
        logger.debug("Received request to create a ticket: {}", dto);
        logger.debug("Authenticated user: {}", authentication.getName());
        logger.debug("User roles: {}", authentication.getAuthorities());

        try {
            TicketResponseDTO res = ticketService.createTicket(dto, authentication);
            var uri = uriBuilder.path("/api/v1/tickets/{id}").buildAndExpand(res.getId()).toUri();
            logger.info("Ticket created successfully with ID: {}", res.getId());
            return ResponseEntity.created(uri).body(res);
        } catch (UnauthorizedException ex) {
            throw new UnauthorizedException("You are not authorized to create a ticket");
        } catch (BadRequestException ex) {
            throw new BadRequestException("Invalid input");
        } catch (Exception ex) {
            logger.error("Error creating ticket", ex);
            throw new RuntimeException("Error creating ticket", ex);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a Ticket by Id")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ticket retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TicketResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized due to missing or invalid JWT token",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "404", description = "Ticket not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)))
    })
    public ResponseEntity<TicketResponseDTO> getTicketById(@PathVariable long id) {
        logger.debug("Fetching ticket with ID: {}", id);
        try {
            TicketResponseDTO ticket = ticketService.getTicketById(id);
            return ResponseEntity.ok(ticket);
        } catch (ResourceNotFoundException ex) {
            logger.error("Ticket not found with ID: {}", id, ex);
            throw new ResourceNotFoundException("Ticket not found with id : '" + id + "'");
        } catch (Exception ex) {
            logger.error("Failed to fetch ticket with ID: {}", id, ex);
            throw new RuntimeException("Failed to fetch ticket", ex);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a Ticket by Id")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ticket updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TicketResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request due to invalid input",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized due to missing or invalid JWT token",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "404", description = "Ticket not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)))
    })
    public ResponseEntity<TicketResponseDTO> updateTicket(
            Authentication authentication,
            @PathVariable long id,
            @RequestBody @Valid TicketCreateDTO dto) {
        logger.debug("Updating ticket with ID: {}", id);
        try {
            TicketResponseDTO updatedTicket = ticketService.updateTicket(id, dto, authentication);
            return ResponseEntity.ok(updatedTicket);
        } catch (ResourceNotFoundException ex) {
            logger.error("Ticket not found with ID: {}", id, ex);
            throw new ResourceNotFoundException("Ticket not found with id : '" + id + "'");
        } catch (BadRequestException ex) {
            logger.error("Invalid input for updating ticket with ID: {}", id, ex);
            throw new BadRequestException("Invalid input");
        } catch (Exception ex) {
            logger.error("Failed to update ticket with ID: {}", id, ex);
            throw new RuntimeException("Failed to update ticket", ex);
        }
    }

    @PutMapping("/{id}/close")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Close a Ticket by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ticket closed successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TicketResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized due to missing or invalid JWT token",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "404", description = "Ticket not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)))
    })
    public ResponseEntity<TicketResponseDTO> closeTicket(
            Authentication authentication,
            @PathVariable long id,
            @RequestBody @Valid TicketCloseDTO dto) {
        logger.debug("Request to close ticket with ID: {}", id);
        try {
            TicketResponseDTO closedTicket = ticketService.closeTicket(id, dto, authentication);
            return ResponseEntity.ok(closedTicket);
        } catch (ResourceNotFoundException ex) {
            logger.error("Ticket not found with ID: {}", id, ex);
            throw new ResourceNotFoundException("Ticket not found with id : '" + id + "'");
        } catch (BadRequestException ex) {
            logger.error("Invalid input for closing ticket with ID: {}", id, ex);
            throw new BadRequestException("Invalid input");
        } catch (Exception ex) {
            logger.error("Failed to close ticket with ID: {}", id, ex);
            throw new RuntimeException("Failed to close ticket", ex);
        }
    }

    @PutMapping("/{id}/open")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Open a Ticket by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ticket opened successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TicketResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized due to missing or invalid JWT token",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "404", description = "Ticket not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)))
    })
    public ResponseEntity<TicketResponseDTO> openTicket(
            Authentication authentication,
            @PathVariable long id,
            @RequestBody @Valid TicketResponseDTO dto) {
        logger.debug("Request to open ticket with ID: {}", id);
        try {
            TicketResponseDTO openedTicket = ticketService.openTicket(id, dto, authentication);
            return ResponseEntity.ok(openedTicket);
        } catch (ResourceNotFoundException ex) {
            logger.error("Ticket not found with ID: {}", id, ex);
            throw new ResourceNotFoundException("Ticket not found with id : '" + id + "'");
        } catch (BadRequestException ex) {
            logger.error("Invalid input for opening ticket with ID: {}", id, ex);
            throw new BadRequestException("Invalid input");
        } catch (Exception ex) {
            logger.error("Failed to open ticket with ID: {}", id, ex);
            throw new RuntimeException("Failed to open ticket", ex);
        }
    }

    @GetMapping
    @Operation(summary = "Get all Tickets")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tickets retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TicketListDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized due to missing or invalid JWT token",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)))
    })
    public ResponseEntity<TicketListDTO> getAllTickets(
            @RequestParam int pageNo,
            @RequestParam int pageSize,
            @RequestParam String sortDir,
            @RequestParam(value = "sortBy", required = false, defaultValue = "id") String... sortBy) {
        logger.debug("Fetching all tickets with page: {}, size: {}, sort direction: {}, sort by: {}", pageNo, pageSize, sortDir, (Object[]) sortBy);
        try {
            TicketListDTO tickets = ticketService.getAllTickets(pageNo, pageSize, sortDir, sortBy);
            return ResponseEntity.ok(tickets);
        } catch (Exception ex) {
            logger.error("Failed to fetch tickets", ex);
            throw new RuntimeException("Failed to fetch tickets", ex);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete a Ticket by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ticket deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized due to missing or invalid JWT token",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "404", description = "Ticket not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)))
    })
    public ResponseEntity<Void> deleteTicket(@PathVariable long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }
}
