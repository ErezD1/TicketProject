package edu.erezd.erezproject.service.impl;

import edu.erezd.erezproject.dto.comment.CommentResponseDTO;
import edu.erezd.erezproject.dto.ticket.TicketCloseDTO;
import edu.erezd.erezproject.dto.ticket.TicketCreateDTO;
import edu.erezd.erezproject.dto.ticket.TicketListDTO;
import edu.erezd.erezproject.dto.ticket.TicketResponseDTO;
import edu.erezd.erezproject.entity.Comment;
import edu.erezd.erezproject.entity.Status;
import edu.erezd.erezproject.entity.Ticket;
import edu.erezd.erezproject.entity.User;
import edu.erezd.erezproject.repository.TicketRepository;
import edu.erezd.erezproject.service.TicketService;
import edu.erezd.erezproject.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);

    @PostConstruct
    private void initTicketModelMapper() {
        modelMapper.typeMap(Ticket.class, TicketResponseDTO.class).addMappings(mapper ->
                mapper.map(src -> src.getUser().getUsername(), TicketResponseDTO::setUsername));
        modelMapper.typeMap(Comment.class, CommentResponseDTO.class).addMappings(mapper ->
                mapper.map(src -> src.getUser().getUsername(), CommentResponseDTO::setUsername));
    }

    @Override
    public TicketResponseDTO createTicket(TicketCreateDTO ticketDTO, Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        if (user == null) {
            throw new IllegalStateException("User not found.");
        }

        Ticket ticket = modelMapper.map(ticketDTO, Ticket.class);
        ticket.setUser(user);
        ticket.setStatus(Status.OPEN);
        ticket.setCreatedAt(LocalDateTime.now());

        Ticket savedTicket = ticketRepository.save(ticket);
        return mapToTicketResponseDTO(savedTicket);
    }


    @Override
    public TicketResponseDTO getTicketById(long id) {
        return mapToTicketResponseDTO(getTicketEntityOrThrow(id));
    }

    @Override
    public TicketResponseDTO updateTicket(long id, TicketCreateDTO ticketDTO, Authentication authentication) {
        Ticket ticket = getTicketEntityOrThrow(id);
        modelMapper.map(ticketDTO, ticket); // Update the ticket fields

        if (authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            if (ticket.getStatus() == null) {
                ticket.setStatus(Status.OPEN); // Ensure status is not null for regular users
            }
        }

        ticket.setLastUpdatedBy(authentication.getName()); // Track the user who last updated the ticket

        return mapToTicketResponseDTO(ticketRepository.save(ticket));
    }




    @Override
    public TicketResponseDTO closeTicket(long id, TicketCloseDTO ticketDTO, Authentication authentication) {
        Ticket ticket = getTicketEntityOrThrow(id);
        if (ticket.getStatus() == Status.CLOSED) {
            throwAlreadyClosedException(ticket);
        }
        ticket.setStatus(Status.CLOSED);
        ticket.setClosingComment(ticketDTO.getClosingComment());
        ticket.setUpdatedAt(LocalDateTime.now());
        ticket.setLastUpdatedBy(authentication.getName());
        return mapToTicketResponseDTO(ticketRepository.save(ticket));
    }

    @Override
    public TicketResponseDTO openTicket(long id, TicketResponseDTO ticketDTO, Authentication authentication) {
        Ticket ticket = getTicketEntityOrThrow(id);
        if (ticket.getStatus() != Status.CLOSED || authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            throw new IllegalStateException("The Ticket is already in Status Open/In Progress.");
        }
        ticket.setClosingComment(ticketDTO.getClosingComment());
        ticket.setStatus(Status.IN_PROGRESS);
        ticket.setLastUpdatedBy(authentication.getName());
        return mapToTicketResponseDTO(ticketRepository.save(ticket));
    }

    @Override
    public TicketListDTO getAllTickets(int pageNo, int pageSize, String sortDir, String... sortBy) {
        Sort.Direction sortDirection = Sort.Direction.fromString(sortDir);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sortDirection, sortBy);
        Page<Ticket> ticketPage = ticketRepository.findAll(pageable);
        List<TicketResponseDTO> ticketResponseDTOs = ticketPage.getContent().stream()
                .map(this::mapToTicketResponseDTO)
                .collect(Collectors.toList());

        return new TicketListDTO(ticketPage.getTotalElements(), ticketPage.getNumber(), ticketPage.getSize(), ticketPage.getTotalPages(), ticketPage.isFirst(), ticketPage.isLast(), ticketResponseDTOs);
    }


    private TicketResponseDTO mapToTicketResponseDTO(Ticket ticket) {
        TicketResponseDTO dto = modelMapper.map(ticket, TicketResponseDTO.class);
        if (ticket.getComments() != null) {
            List<CommentResponseDTO> commentDtos = ticket.getComments().stream()
                    .map(comment -> modelMapper.map(comment, CommentResponseDTO.class))
                    .collect(Collectors.toList());
            dto.setComments(commentDtos);
        }
        return dto;
    }

    public Ticket getTicketEntityOrThrow(long id) {
        return ticketRepository.findById(id).orElseThrow(() -> new RuntimeException("Ticket not found"));
    }

    @Override
    public void deleteTicket(long id) {
        ticketRepository.deleteById(id);
    }

    private void throwAlreadyClosedException(Ticket ticket) {
        throw new IllegalStateException("Ticket is already closed as of " + ticket.getUpdatedAt().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
    }

    private void checkAndUpdateStatusForAdmin(Authentication authentication, Ticket ticket) {
        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            // Admin can set the status
            // Here you might want to handle the new status from the DTO, if provided.
        }
    }
}
