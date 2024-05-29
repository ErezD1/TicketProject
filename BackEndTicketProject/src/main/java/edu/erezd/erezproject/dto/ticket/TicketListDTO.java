package edu.erezd.erezproject.dto.ticket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketListDTO {
    private long totalTickets;
    private int pageNo;
    private int pageSize;
    private int totalPages;
    private boolean isFirst;
    private boolean isLast;
    private List<TicketResponseDTO> tickets;
}