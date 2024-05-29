package edu.erezd.erezproject.dto.ticket;

import edu.erezd.erezproject.dto.comment.CommentResponseDTO;
import edu.erezd.erezproject.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketResponseDTO {
    private Long id;
    private String subject;
    private String description;
    private Status status;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<CommentResponseDTO> comments;
    private String closingComment;
    private String lastUpdatedBy;
}