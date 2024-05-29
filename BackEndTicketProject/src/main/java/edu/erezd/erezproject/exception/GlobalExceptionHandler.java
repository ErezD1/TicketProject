package edu.erezd.erezproject.exception;

import edu.erezd.erezproject.dto.error.ErrorDTO;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDTO> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        ErrorDTO errorDTO = ErrorDTO.builder()
                .error("Access Denied")
                .message("You do not have permission to perform this action.")
                .status(HttpStatus.FORBIDDEN.value())
                .timestamp(ZonedDateTime.now())
                .path(request.getDescription(false))
                .build();
        return new ResponseEntity<>(errorDTO, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorDTO> handleAuthenticationException(AuthenticationException ex, WebRequest request) {
        ErrorDTO errorDTO = ErrorDTO.builder()
                .error("Unauthorized")
                .message("Authentication failed.")
                .status(HttpStatus.UNAUTHORIZED.value())
                .timestamp(ZonedDateTime.now())
                .path(request.getDescription(false))
                .build();
        return new ResponseEntity<>(errorDTO, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errors);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDTO> handleDataIntegrityViolation(DataIntegrityViolationException ex, WebRequest request) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setTimestamp(ZonedDateTime.now());

        Throwable rootCause = ex.getRootCause() != null ? ex.getRootCause() : ex.getCause();
        if (rootCause != null) {
            ConstraintViolationException cve = (ConstraintViolationException) rootCause;
            String sqlMessage = cve.getSQLException().getMessage();

            errorDTO.setStatus(HttpStatus.CONFLICT.value());
            errorDTO.setError("Conflict");
            if (sqlMessage.contains("UQ_USER_NAME")) {
                errorDTO.setField("username");
                errorDTO.setMessage("Username already exists");
            } else if (sqlMessage.contains("UQ_USER_EMAIL")) {
                errorDTO.setField("email");
                errorDTO.setMessage("Email already exists");
            } else {
                errorDTO.setMessage("A database constraint violation occurred.");
            }
        } else {
            errorDTO.setStatus(HttpStatus.CONFLICT.value());
            errorDTO.setError("Data Integrity Violation");
            errorDTO.setMessage("A data integrity issue occurred that prevented the request from completing.");
        }
        errorDTO.setPath(request.getDescription(false));
        return new ResponseEntity<>(errorDTO, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDTO> handleBadRequestException(BadRequestException ex, WebRequest request) {
        ErrorDTO errorDTO = ErrorDTO.builder()
                .error("Bad Request")
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(ZonedDateTime.now())
                .path(request.getDescription(false))
                .build();
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorDTO errorDTO = ErrorDTO.builder()
                .error("Not Found")
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .timestamp(ZonedDateTime.now())
                .path(request.getDescription(false))
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorDTO);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorDTO> handleUnauthorizedException(UnauthorizedException ex, WebRequest request) {
        ErrorDTO errorDTO = ErrorDTO.builder()
                .error("Unauthorized")
                .message(ex.getMessage())
                .status(HttpStatus.UNAUTHORIZED.value())
                .timestamp(ZonedDateTime.now())
                .path(request.getDescription(false))
                .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleGlobalException(Exception ex, WebRequest request) {
        ErrorDTO errorDTO = ErrorDTO.builder()
                .error("Internal Server Error")
                .message(ex.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(ZonedDateTime.now())
                .path(request.getDescription(false))
                .details(ex.getLocalizedMessage())
                .build();
        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
