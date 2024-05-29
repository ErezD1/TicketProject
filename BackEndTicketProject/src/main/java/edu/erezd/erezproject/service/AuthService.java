package edu.erezd.erezproject.service;

import edu.erezd.erezproject.dto.login.LoginRequestDTO;
import edu.erezd.erezproject.dto.login.LoginResponseDTO;
import edu.erezd.erezproject.dto.user.UserRequestDTO;
import edu.erezd.erezproject.dto.user.UserResponseDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    UserResponseDTO register(UserRequestDTO dto);

    LoginResponseDTO login(LoginRequestDTO dto);
}
