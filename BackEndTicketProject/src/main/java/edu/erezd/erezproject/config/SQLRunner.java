package edu.erezd.erezproject.config;

import edu.erezd.erezproject.entity.Role;
import edu.erezd.erezproject.entity.Status;
import edu.erezd.erezproject.entity.Ticket;
import edu.erezd.erezproject.entity.User;
import edu.erezd.erezproject.repository.RoleRepository;
import edu.erezd.erezproject.repository.TicketRepository;
import edu.erezd.erezproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class SQLRunner implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
//    @Transactional / @Primary
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {
            var adminRole = roleRepository.save(new Role(1L, "ROLE_ADMIN"));
            var userRole = roleRepository.save(new Role(2L, "ROLE_USER"));

            userRepository.save(
                    new User(
                            1L,
                            "admin",
                            "admin@gmail.com",
                            passwordEncoder.encode("Passw0rd1!"),
                            Set.of(adminRole),
                            Set.of()
                    )
            );

            userRepository.save(
                    new User(
                            2L,
                            "user",
                            "user@gmail.com",
                            passwordEncoder.encode("Passw0rd1!"),
                            Set.of(userRole),
                            Set.of()
                    )
            );
        }
    }
}
