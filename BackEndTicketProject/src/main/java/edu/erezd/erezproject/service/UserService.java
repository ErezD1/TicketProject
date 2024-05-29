package edu.erezd.erezproject.service;

import edu.erezd.erezproject.dto.user.UserCreateDTO;
import edu.erezd.erezproject.dto.user.UserResponseDTO;
import edu.erezd.erezproject.entity.User;

import java.util.List;

public interface UserService {

    UserResponseDTO createUser(UserCreateDTO dto);

    UserResponseDTO getUserById(long id);

    User findByUsername(String username);

    UserResponseDTO updateUser(long id, UserCreateDTO dto);

    List<UserResponseDTO> getAllUsers();

    void deleteUserById(long id);

    User getUserEntityOrThrow(long id);

}
