package edu.erezd.erezproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class TicketExceptionHandler {
    // Custom exception handler
    @ExceptionHandler(TicketException.class)
    public ResponseEntity<Object> handleTicketException(TicketException ex, WebRequest request) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage(), Map.of("path", request.getDescription(false)));
    }

    // SQL constraint violation exception handler
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Object> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex, WebRequest request) {
        return buildResponseEntity(HttpStatus.CONFLICT, "Database error: " + ex.getMessage(), Map.of("path", request.getDescription(false)));
    }

    // General validation error handler
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return buildResponseEntity(HttpStatus.BAD_REQUEST, "Validation error", errors);
    }

    // Catch-all exception handler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", Map.of("path", request.getDescription(false)));
    }

    // Helper method to build the ResponseEntity, adjusted for flexibility
    private ResponseEntity<Object> buildResponseEntity(HttpStatus status, String message, Map<String, ?> details) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);

        if (details != null) {
            body.putAll(details); // Now supports any details map
        }

        return new ResponseEntity<>(body, status);
    }

}