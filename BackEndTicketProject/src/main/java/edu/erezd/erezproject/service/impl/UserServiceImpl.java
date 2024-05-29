package edu.erezd.erezproject.service.impl;

import edu.erezd.erezproject.dto.user.UserCreateDTO;
import edu.erezd.erezproject.dto.user.UserResponseDTO;
import edu.erezd.erezproject.entity.Role;
import edu.erezd.erezproject.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import edu.erezd.erezproject.entity.User;
import edu.erezd.erezproject.exception.ResourceNotFoundException;
import edu.erezd.erezproject.repository.UserRepository;
import edu.erezd.erezproject.service.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponseDTO createUser(UserCreateDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        Set<Role> roles = new HashSet<>();

        // Validate and add roles
        dto.getRoles().forEach(roleName -> {
            Role role = roleRepository.findRoleByNameIgnoreCase(roleName)
                    .orElseThrow(() -> new ResourceNotFoundException("Role", "name", roleName));
            roles.add(role);
        });

        user.setRoles(roles);
        User saved = userRepository.save(user);
        UserResponseDTO responseDTO = modelMapper.map(saved, UserResponseDTO.class);
        responseDTO.setRoles(roles.stream().map(Role::getName).collect(Collectors.toSet()));
        return responseDTO;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponseDTO getUserById(long id) {
        User user = getUserEntityOrThrow(id); // Use the helper method to get the user or throw an exception
        UserResponseDTO responseDTO = modelMapper.map(user, UserResponseDTO.class);
        responseDTO.setRoles(user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()));
        return responseDTO;
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponseDTO updateUser(long id, UserCreateDTO dto) {
        User user = getUserEntityOrThrow(id); // Use the helper method to ensure the user exists
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());

        // Convert roles from strings to Role entities
        Set<Role> updatedRoles = dto.getRoles().stream()
                .map(roleName -> roleRepository.findRoleByNameIgnoreCase(roleName)
                        .orElseThrow(() -> new ResourceNotFoundException("Role", "name", roleName)))
                .collect(Collectors.toSet());

        user.setRoles(updatedRoles);

        // Consider adding more fields to update as necessary
        User saved = userRepository.save(user);
        UserResponseDTO responseDTO = modelMapper.map(saved, UserResponseDTO.class);
        responseDTO.setRoles(updatedRoles.stream().map(Role::getName).collect(Collectors.toSet()));
        return responseDTO;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUserById(long id) {
        User user = getUserEntityOrThrow(id);
        userRepository.deleteById(id);
        UserResponseDTO responseDTO = modelMapper.map(user, UserResponseDTO.class);
        responseDTO.setRoles(user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public List<UserResponseDTO> getAllUsers() {
        try {
            List<User> users = userRepository.findAll();
            return users.stream()
                    .map(user -> {
                        UserResponseDTO dto = modelMapper.map(user, UserResponseDTO.class);
                        dto.setRoles(user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()));
                        return dto;
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve users", e);
        }
    }

    public User getUserEntityOrThrow(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }
}
