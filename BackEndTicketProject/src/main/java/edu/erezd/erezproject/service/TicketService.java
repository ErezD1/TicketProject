package edu.erezd.erezproject.service;

import edu.erezd.erezproject.dto.ticket.TicketCloseDTO;
import edu.erezd.erezproject.dto.ticket.TicketCreateDTO;
import edu.erezd.erezproject.dto.ticket.TicketListDTO;
import edu.erezd.erezproject.dto.ticket.TicketResponseDTO;
import edu.erezd.erezproject.entity.Ticket;
import org.springframework.security.core.Authentication;

public interface TicketService {

    TicketResponseDTO createTicket(TicketCreateDTO ticketDTO, Authentication authentication);

    TicketResponseDTO getTicketById(long id);

    TicketResponseDTO updateTicket(long id, TicketCreateDTO ticketDTO, Authentication authentication);

    TicketResponseDTO closeTicket(long id, TicketCloseDTO ticketDTO, Authentication authentication);

    TicketResponseDTO openTicket(long id, TicketResponseDTO ticketDTO, Authentication authentication);

    TicketListDTO getAllTickets(int pageNo, int pageSize, String sortDir, String... sortBy);

    Ticket getTicketEntityOrThrow(long id);

    void deleteTicket(long id);
}
