package edu.erezd.erezproject.dto.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ErrorDTO {
    private String message;
    private int status;
    private String error;
    private String path;
    private ZonedDateTime timestamp;
    private String details;
    private String field;
}