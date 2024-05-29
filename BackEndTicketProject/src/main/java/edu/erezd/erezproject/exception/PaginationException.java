package edu.erezd.erezproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PaginationException extends TicketException{
    public PaginationException(String message) {
        super(message);
    }
}
//page=0 =>totalPages