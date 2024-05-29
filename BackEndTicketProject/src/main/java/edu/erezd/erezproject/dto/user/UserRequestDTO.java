package edu.erezd.erezproject.dto.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;



public record UserRequestDTO(
        @NotNull
        @Size(min = 2)
        String username,

        @NotNull
        @Pattern(regexp = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
        String email,

        @NotNull
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,32}$",
                message = "Password must contain at least 1 uppercase letter, 1 lowercase letter, 1 digit, and 1 special character"
        )
        String password) {

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}


