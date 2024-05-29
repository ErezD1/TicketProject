package edu.erezd.erezproject.controller;

import edu.erezd.erezproject.config.RSAKeyProperties;
import edu.erezd.erezproject.dto.ticket.TicketCloseDTO;
import edu.erezd.erezproject.dto.ticket.TicketCreateDTO;
import edu.erezd.erezproject.dto.ticket.TicketListDTO;
import edu.erezd.erezproject.dto.ticket.TicketResponseDTO;
import edu.erezd.erezproject.entity.Status;
import edu.erezd.erezproject.exception.ResourceNotFoundException;
import edu.erezd.erezproject.security.CustomAuthenticationEntryPoint;
import edu.erezd.erezproject.security.SecurityConfig;
import edu.erezd.erezproject.security.TestRSAKeyConfig;
import edu.erezd.erezproject.service.TicketService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TicketController.class)
@Import({SecurityConfig.class, TestRSAKeyConfig.class, CustomAuthenticationEntryPoint.class})
@EnableConfigurationProperties(RSAKeyProperties.class)
public class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketService ticketService;

    @Autowired
    private JwtEncoder jwtEncoder;

    private static JwtTokenUtil jwtTokenUtil;
    private static String userToken;
    private static String adminToken;

    @BeforeAll
    static void setUp(@Autowired JwtEncoder jwtEncoder) {
        jwtTokenUtil = new JwtTokenUtil(jwtEncoder);
        userToken = jwtTokenUtil.generateToken("user", Set.of("ROLE_USER"));
        adminToken = jwtTokenUtil.generateToken("admin", Set.of("ROLE_ADMIN"));
    }

    @Test
    void testCreateTicket_Success() throws Exception {
        TicketCreateDTO ticketCreateDTO = new TicketCreateDTO();
        ticketCreateDTO.setSubject("Test Ticket");
        ticketCreateDTO.setDescription("Test Description");

        TicketResponseDTO ticketResponseDTO = TicketResponseDTO.builder()
                .id(1L)
                .subject("Test Ticket")
                .description("Test Description")
                .build();

        Mockito.when(ticketService.createTicket(Mockito.any(TicketCreateDTO.class), Mockito.any()))
                .thenReturn(ticketResponseDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/tickets")
                        .header("Authorization", "Bearer " + userToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"subject\": \"Test Ticket\", \"description\": \"Test Description\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.subject").value("Test Ticket"))
                .andExpect(jsonPath("$.description").value("Test Description"));
    }

    @Test
    void testCreateTicket_BadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/tickets")
                        .header("Authorization", "Bearer " + userToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"subject\": \"\", \"description\": \"\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.subject").value("Subject is mandatory and must be between 2 and 128 characters"))
                .andExpect(jsonPath("$.description").value("Description is mandatory and must be between 2 and 2147483647 characters"));
    }

    @Test
    void testCreateTicket_MissingJWTToken() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/tickets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"subject\": \"Test Ticket\", \"description\": \"Test Description\"}"))
                .andExpect(status().isUnauthorized())
                .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    void testCreateTicket_InvalidJWTToken() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/tickets")
                        .header("Authorization", "Bearer invalid_token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"subject\": \"Test Ticket\", \"description\": \"Test Description\"}"))
                .andExpect(status().isUnauthorized())
                .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.message").value("Authentication failed."));
    }

    @Test
    void testCreateTicket_InsufficientPermissions() throws Exception {
        String insufficientRoleToken = jwtTokenUtil.generateToken("user", Set.of("ROLE_GUEST"));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/tickets")
                        .header("Authorization", "Bearer " + insufficientRoleToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"subject\": \"Test Ticket\", \"description\": \"Test Description\"}"))
                .andExpect(status().isForbidden())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error").value("Access Denied"))
                .andExpect(jsonPath("$.message").value("You do not have permission to perform this action."));
    }

    @Test
    void testGetTicketById_Success() throws Exception {
        TicketResponseDTO ticketResponseDTO = TicketResponseDTO.builder()
                .id(1L)
                .subject("Test Ticket")
                .build();

        Mockito.when(ticketService.getTicketById(1L)).thenReturn(ticketResponseDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tickets/1")
                        .header("Authorization", "Bearer " + userToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.subject").value("Test Ticket"));
    }

    @Test
    void testGetTicketById_NotFound() throws Exception {
        Mockito.when(ticketService.getTicketById(1L)).thenThrow(new ResourceNotFoundException("Ticket", "id", 1L));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tickets/1")
                        .header("Authorization", "Bearer " + userToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Ticket not found with id : '1'"));
    }

    @Test
    void testCloseTicket_Success() throws Exception {
        TicketCloseDTO ticketCloseDTO = new TicketCloseDTO();
        ticketCloseDTO.setId(1L);

        TicketResponseDTO ticketResponseDTO = TicketResponseDTO.builder()
                .id(1L)
                .subject("Test Ticket")
                .build();

        Mockito.when(ticketService.closeTicket(Mockito.anyLong(), Mockito.any(TicketCloseDTO.class), Mockito.any()))
                .thenReturn(ticketResponseDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/tickets/1/close")
                        .header("Authorization", "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.subject").value("Test Ticket"));
    }

    @Test
    void testCloseTicket_NotFound() throws Exception {
        Mockito.when(ticketService.closeTicket(Mockito.anyLong(), Mockito.any(TicketCloseDTO.class), Mockito.any()))
                .thenThrow(new ResourceNotFoundException("Ticket", "id", 1L));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/tickets/1/close")
                        .header("Authorization", "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1}"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Ticket not found with id : '1'"));
    }

    @Test
    void testOpenTicket_Success() throws Exception {
        TicketResponseDTO ticketResponseDTO = TicketResponseDTO.builder()
                .id(1L)
                .subject("Test Ticket")
                .build();

        Mockito.when(ticketService.openTicket(Mockito.anyLong(), Mockito.any(TicketResponseDTO.class), Mockito.any()))
                .thenReturn(ticketResponseDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/tickets/1/open")
                        .header("Authorization", "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.subject").value("Test Ticket"));
    }

    @Test
    void testOpenTicket_NotFound() throws Exception {
        Mockito.when(ticketService.openTicket(Mockito.anyLong(), Mockito.any(TicketResponseDTO.class), Mockito.any()))
                .thenThrow(new ResourceNotFoundException("Ticket", "id", 1L));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/tickets/1/open")
                        .header("Authorization", "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1}"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Ticket not found with id : '1'"));
    }

    @Test
    void testGetAllTickets_Success() throws Exception {
        TicketListDTO ticketListDTO = new TicketListDTO();
        ticketListDTO.setTotalTickets(1L);
        ticketListDTO.setTotalPages(1);
        ticketListDTO.setTickets(List.of(TicketResponseDTO.builder()
                .id(1L)
                .subject("Test Ticket")
                .description("Test Description")
                .status(Status.OPEN)
                .username("user")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .comments(List.of())
                .build()));

        Mockito.when(ticketService.getAllTickets(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString(), Mockito.any()))
                .thenReturn(ticketListDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tickets")
                        .header("Authorization", "Bearer " + userToken)
                        .param("pageNo", "0")
                        .param("pageSize", "10")
                        .param("sortDir", "asc")
                        .param("sortBy", "id")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalTickets").value(1L))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.tickets[0].id").value(1L))
                .andExpect(jsonPath("$.tickets[0].subject").value("Test Ticket"));
    }

    @Test
    void testGetAllTickets_SortByDescription() throws Exception {
        TicketListDTO ticketListDTO = new TicketListDTO();
        ticketListDTO.setTotalTickets(1L);
        ticketListDTO.setTotalPages(1);
        ticketListDTO.setTickets(List.of(TicketResponseDTO.builder()
                .id(1L)
                .subject("Test Ticket")
                .description("Test Description")
                .status(Status.OPEN)
                .username("user")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .comments(List.of())
                .build()));

        Mockito.when(ticketService.getAllTickets(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString(), Mockito.any()))
                .thenReturn(ticketListDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tickets")
                        .header("Authorization", "Bearer " + userToken)
                        .param("pageNo", "0")
                        .param("pageSize", "10")
                        .param("sortDir", "asc")
                        .param("sortBy", "description")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalTickets").value(1L))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.tickets[0].id").value(1L))
                .andExpect(jsonPath("$.tickets[0].description").value("Test Description"));
    }

    @Test
    void testUpdateTicket_Success() throws Exception {
        TicketResponseDTO ticketResponseDTO = TicketResponseDTO.builder()
                .id(1L)
                .subject("Updated Ticket")
                .description("Updated Description")
                .build();

        Mockito.when(ticketService.updateTicket(Mockito.anyLong(), Mockito.any(TicketCreateDTO.class), Mockito.any()))
                .thenReturn(ticketResponseDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/tickets/1")
                        .header("Authorization", "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"subject\": \"Updated Ticket\", \"description\": \"Updated Description\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.subject").value("Updated Ticket"))
                .andExpect(jsonPath("$.description").value("Updated Description"));
    }

    @Test
    void testUpdateTicket_NotFound() throws Exception {
        Mockito.when(ticketService.updateTicket(Mockito.anyLong(), Mockito.any(TicketCreateDTO.class), Mockito.any()))
                .thenThrow(new ResourceNotFoundException("Ticket", "id", 1L));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/tickets/1")
                        .header("Authorization", "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"subject\": \"Updated Ticket\", \"description\": \"Updated Description\"}"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Ticket not found with id : '1'"));
    }

    @Test
    void testDeleteTicket_Success() throws Exception {
        Mockito.doNothing().when(ticketService).deleteTicket(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/tickets/1")
                        .header("Authorization", "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

}