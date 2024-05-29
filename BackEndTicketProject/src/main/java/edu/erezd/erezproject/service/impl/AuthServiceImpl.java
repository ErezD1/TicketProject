package edu.erezd.erezproject.service.impl;

import edu.erezd.erezproject.dto.login.LoginRequestDTO;
import edu.erezd.erezproject.dto.login.LoginResponseDTO;
import edu.erezd.erezproject.dto.user.UserRequestDTO;
import edu.erezd.erezproject.dto.user.UserResponseDTO;
import edu.erezd.erezproject.entity.User;
import edu.erezd.erezproject.exception.UserAlreadyExistsException;
import edu.erezd.erezproject.repository.RoleRepository;
import edu.erezd.erezproject.repository.UserRepository;
import edu.erezd.erezproject.service.AuthService;
import edu.erezd.erezproject.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    //props:
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final JWTService jwtService;

    @Override
    public UserResponseDTO register(UserRequestDTO dto) {
        //check that the user does not exist email/username:
        userRepository.findUserByUsernameIgnoreCaseOrEmailIgnoreCase(dto.username(), dto.email()).ifPresent((u) -> {
            throw new UserAlreadyExistsException(u.getUsername(), u.getEmail());
        });

        var user = modelMapper.map(dto, User.class);
        //encrypt password
        user.setPassword(passwordEncoder.encode(dto.password()));

        var role = roleRepository.findRoleByNameIgnoreCase("ROLE_USER").orElseThrow();
        user.setRoles(Set.of(role));

        var saved = userRepository.save(user);

        return modelMapper.map(saved, UserResponseDTO.class);
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO dto) {
        var user = getUserEntityOrThrow(dto.username());

        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new RuntimeException("Username or password don't match");
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                dto.password(),
                user.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getName())).toList()
        );

        var jwt = jwtService.jwtToken(authentication);
        return new LoginResponseDTO(jwt);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //fetch our entity from database:
        var user = getUserEntityOrThrow(username);

        //map the roles for Spring:
        //map our Set<Role> to Set<spring.Role>
        var roles =
                user.getRoles().stream()
                        .map(r -> new SimpleGrantedAuthority(r.getName()))
                        .collect(Collectors.toSet());

        //map the user to Spring User:
        //return new Spring User:
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);
    }

    private User getUserEntityOrThrow(String username) {
        return userRepository.findUserByUsernameIgnoreCase(username).orElseThrow(
                () -> new UsernameNotFoundException(username)
        );
    }
}
