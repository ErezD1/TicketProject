package edu.erezd.erezproject.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserAlreadyExistsException extends TicketException {
    public UserAlreadyExistsException(String username, String email) {
        super("User with email: " + email + " & \n username: " + username + " already exists");
    }
}