package edu.erezd.erezproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class AuthenticationException extends TicketException {
    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException() {
        super("Not Authenticated");
    }
}