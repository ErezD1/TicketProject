package edu.erezd.erezproject.controller;

import edu.erezd.erezproject.config.RSAKeyProperties;
import edu.erezd.erezproject.dto.user.UserCreateDTO;
import edu.erezd.erezproject.dto.user.UserResponseDTO;
import edu.erezd.erezproject.entity.Role;
import edu.erezd.erezproject.entity.User;
import edu.erezd.erezproject.exception.ResourceNotFoundException;
import edu.erezd.erezproject.security.CustomAuthenticationEntryPoint;
import edu.erezd.erezproject.security.SecurityConfig;
import edu.erezd.erezproject.security.TestRSAKeyConfig;
import edu.erezd.erezproject.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@Import({SecurityConfig.class, TestRSAKeyConfig.class, CustomAuthenticationEntryPoint.class})
@EnableConfigurationProperties(RSAKeyProperties.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

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
    void testCreateUser_Success() throws Exception {
        UserCreateDTO userCreateDTO = new UserCreateDTO();
        userCreateDTO.setUsername("testuser");
        userCreateDTO.setPassword("password");

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(1L);
        userResponseDTO.setUsername("testuser");

        Mockito.when(userService.createUser(Mockito.any(UserCreateDTO.class)))
                .thenReturn(userResponseDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users/create")
                        .header("Authorization", "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"testuser\", \"password\": \"password\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.username").value("testuser"));
    }

    @Test
    void testCreateUser_BadRequest() throws Exception {
        Mockito.when(userService.createUser(Mockito.any(UserCreateDTO.class)))
                .thenThrow(new IllegalArgumentException("Invalid input"));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users/create")
                        .header("Authorization", "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"\", \"password\": \"\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Invalid input"));
    }

    @Test
    void testCreateUser_Conflict() throws Exception {
        Mockito.when(userService.createUser(Mockito.any(UserCreateDTO.class)))
                .thenThrow(DataIntegrityViolationException.class);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users/create")
                        .header("Authorization", "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"testuser\", \"password\": \"password\"}"))
                .andExpect(status().isConflict());
    }

    @Test
    void testGetUserById_Success() throws Exception {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(1L);
        userResponseDTO.setUsername("testuser");

        Mockito.when(userService.getUserById(1L)).thenReturn(userResponseDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/1")
                        .header("Authorization", "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.username").value("testuser"));
    }

    @Test
    void testGetUserById_NotFound() throws Exception {
        Mockito.when(userService.getUserById(1L)).thenThrow(new ResourceNotFoundException("User", "id", 1L));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/1")
                        .header("Authorization", "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("User not found with id : '1'"));
    }

    @Test
    void testGetAllUsers_Success() throws Exception {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(1L);
        userResponseDTO.setUsername("testuser");

        Mockito.when(userService.getAllUsers()).thenReturn(List.of(userResponseDTO));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/all")
                        .header("Authorization", "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.users[0].id").value(1L))
                .andExpect(jsonPath("$.users[0].username").value("testuser"));
    }

    @Test
    void testUpdateUser_Success() throws Exception {
        UserCreateDTO userCreateDTO = new UserCreateDTO();
        userCreateDTO.setUsername("updateduser");
        userCreateDTO.setPassword("newpassword");

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(1L);
        userResponseDTO.setUsername("updateduser");

        Mockito.when(userService.updateUser(Mockito.anyLong(), Mockito.any(UserCreateDTO.class)))
                .thenReturn(userResponseDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/users/1")
                        .header("Authorization", "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"updateduser\", \"password\": \"newpassword\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.username").value("updateduser"));
    }

    @Test
    void testDeleteUser_Success() throws Exception {
        Mockito.doNothing().when(userService).deleteUserById(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/users/1")
                        .header("Authorization", "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void testFindByUsername_Success() throws Exception {
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("testuser@example.com");
        user.setRoles(Set.of(new Role(1L, "ROLE_USER")));

        Mockito.when(userService.findByUsername("testuser")).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/find/testuser")
                        .header("Authorization", "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testuser"))
                .andExpect(jsonPath("$.email").value("testuser@example.com"))
                .andExpect(jsonPath("$.roles[0]").value("ROLE_USER"));
    }

    @Test
    void testFindByUsername_NotFound() throws Exception {
        Mockito.when(userService.findByUsername("testuser")).thenThrow(new ResourceNotFoundException("User", "username", "testuser"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/find/testuser")
                        .header("Authorization", "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("User not found with username : 'testuser'"));
    }
}
