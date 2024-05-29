package edu.erezd.erezproject.dto.ticket;

import edu.erezd.erezproject.entity.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketCreateDTO {

    @NotBlank(message = "Subject is mandatory")
    @Size(min = 2, max = 128, message = "Subject is mandatory and must be between 2 and 128 characters")
    private String subject;

    @NotBlank(message = "Description is mandatory")
    @Size(min = 2, message = "Description is mandatory and must be between 2 and 2147483647 characters")
    private String description;

    private Status status;
}

