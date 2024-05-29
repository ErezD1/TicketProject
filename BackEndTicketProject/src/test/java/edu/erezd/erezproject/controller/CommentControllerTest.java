package edu.erezd.erezproject.controller;

import edu.erezd.erezproject.config.RSAKeyProperties;
import edu.erezd.erezproject.dto.comment.CommentRequestDTO;
import edu.erezd.erezproject.dto.comment.CommentResponseDTO;
import edu.erezd.erezproject.exception.ResourceNotFoundException;
import edu.erezd.erezproject.security.CustomAuthenticationEntryPoint;
import edu.erezd.erezproject.security.SecurityConfig;
import edu.erezd.erezproject.security.TestRSAKeyConfig;
import edu.erezd.erezproject.service.CommentService;
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

import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CommentController.class)
@Import({SecurityConfig.class, TestRSAKeyConfig.class, CustomAuthenticationEntryPoint.class})
@EnableConfigurationProperties(RSAKeyProperties.class)
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

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
    void testCreateComment_Success() throws Exception {
        CommentRequestDTO commentRequestDTO = new CommentRequestDTO();
        commentRequestDTO.setContent("Test Comment");

        CommentResponseDTO commentResponseDTO = CommentResponseDTO.builder()
                .id(1L)
                .content("Test Comment")
                .build();

        Mockito.when(commentService.createComment(Mockito.anyLong(), Mockito.any(CommentRequestDTO.class), Mockito.any()))
                .thenReturn(commentResponseDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/tickets/1/comments")
                        .header("Authorization", "Bearer " + userToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"content\": \"Test Comment\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.content").value("Test Comment"));
    }

    @Test
    void testCreateComment_BadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/tickets/1/comments")
                        .header("Authorization", "Bearer " + userToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"content\": \"\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.content").value("size must be between 1 and 512"));
    }

    @Test
    void testCreateComment_MissingJWTToken() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/tickets/1/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"content\": \"Test Comment\"}"))
                .andExpect(status().isUnauthorized())
                .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    void testCreateComment_InvalidJWTToken() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/tickets/1/comments")
                        .header("Authorization", "Bearer invalid_token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"content\": \"Test Comment\"}"))
                .andExpect(status().isUnauthorized())
                .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.message").value("Authentication failed."));
    }

    @Test
    void testUpdateComment_Success() throws Exception {
        CommentRequestDTO commentRequestDTO = new CommentRequestDTO();
        commentRequestDTO.setContent("Updated Comment");

        CommentResponseDTO commentResponseDTO = CommentResponseDTO.builder()
                .id(1L)
                .content("Updated Comment")
                .build();

        Mockito.when(commentService.updateCommentById(Mockito.anyLong(), Mockito.any(CommentRequestDTO.class), Mockito.any()))
                .thenReturn(commentResponseDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/tickets/1/comments/1")
                        .header("Authorization", "Bearer " + userToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"content\": \"Updated Comment\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.content").value("Updated Comment"));
    }

    @Test
    void testUpdateComment_NotFound() throws Exception {
        Mockito.when(commentService.updateCommentById(Mockito.anyLong(), Mockito.any(CommentRequestDTO.class), Mockito.any()))
                .thenThrow(new ResourceNotFoundException("Comment", "id", 1L));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/tickets/1/comments/1")
                        .header("Authorization", "Bearer " + userToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"content\": \"Updated Comment\"}"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Comment not found with id : '1'"));
    }

    @Test
    void testDeleteComment_Success() throws Exception {
        Mockito.doNothing().when(commentService).deleteCommentById(Mockito.anyLong(), Mockito.any());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/tickets/1/comments/1")
                        .header("Authorization", "Bearer " + userToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteComment_NotFound() throws Exception {
        Mockito.doThrow(new ResourceNotFoundException("Comment", "id", 1L))
                .when(commentService).deleteCommentById(Mockito.anyLong(), Mockito.any());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/tickets/1/comments/1")
                        .header("Authorization", "Bearer " + userToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Comment not found with id : '1'"));
    }
}
