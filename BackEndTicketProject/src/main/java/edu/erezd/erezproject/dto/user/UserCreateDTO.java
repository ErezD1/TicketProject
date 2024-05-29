package edu.erezd.erezproject.dto.user;

import edu.erezd.erezproject.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateDTO {


    private String username;
    private String password;
    private String email;
    private Set<String> roles;

}
