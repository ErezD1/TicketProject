package edu.erezd.erezproject.service.impl;

import edu.erezd.erezproject.dto.comment.CommentListDTO;
import edu.erezd.erezproject.dto.comment.CommentRequestDTO;
import edu.erezd.erezproject.dto.comment.CommentResponseDTO;
import edu.erezd.erezproject.entity.Comment;
import edu.erezd.erezproject.entity.Status;
import edu.erezd.erezproject.entity.Ticket;
import edu.erezd.erezproject.entity.User;
import edu.erezd.erezproject.exception.AuthenticationException;
import edu.erezd.erezproject.exception.ResourceNotFoundException;
import edu.erezd.erezproject.exception.TicketException;
import edu.erezd.erezproject.repository.CommentRepository;
import edu.erezd.erezproject.service.CommentService;
import edu.erezd.erezproject.service.TicketService;
import edu.erezd.erezproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final TicketService ticketService;
    private final ModelMapper modelMapper;
    private final CommentRepository commentRepository;
    private final UserService userService;

    @Override
    public CommentResponseDTO createComment(long ticketId, CommentRequestDTO dto, Authentication authentication) {
        Ticket ticket = ticketService.getTicketEntityOrThrow(ticketId);
        User user = getUserFromAuthentication(authentication);

        // Check if the ticket is closed
        if (ticket.getStatus() == Status.CLOSED) {
            // Get the latest admin comment and closing comment from the ticket
            Comment latestAdminComment = commentRepository.findLatestAdminCommentByTicketId(ticketId);
            String closingComment = ticket.getClosingComment();

            String errorMessage = "This ticket is already closed.";
            if (latestAdminComment != null && closingComment != null) {
                errorMessage += " It was closed by " + latestAdminComment.getUser().getUsername() +
                                " on " + ticket.getUpdatedAt().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) +
                                ". Closing comment: " + closingComment +
                                ". Last admin comment: " + latestAdminComment.getContent();
            }

            throw new TicketException(errorMessage);
        }

        // If the ticket is not closed, proceed with creating the comment
        Comment comment = modelMapper.map(dto, Comment.class);
        comment.setTicket(ticket);
        comment.setUser(user);

        return saveAndMapToResponseDTO(comment);
    }


    @Override
    public CommentResponseDTO updateCommentById(long id, CommentRequestDTO dto, Authentication authentication) {
        Comment comment = getCommentEntityOrThrow(id);
        Ticket ticket = comment.getTicket();

        // Check if the ticket is closed
        if (ticket.getStatus() == Status.CLOSED) {
            throw new IllegalStateException("Cannot update comment for a closed ticket.");
        }

        // Check permissions
        User user = checkPermissions(authentication, comment);

        // Update the comment
        Comment updatedComment = modelMapper.map(dto, Comment.class);
        updatedComment.setId(id);
        updatedComment.setTicket(ticket);
        updatedComment.setCreatedAt(comment.getCreatedAt());
        updatedComment.setUpdatedAt(LocalDateTime.now());
        updatedComment.setUser(user);

        return saveAndMapToResponseDTO(updatedComment);
    }

    @Override
    public void deleteCommentById(long id, Authentication authentication) {
        Comment comment = getCommentEntityOrThrow(id);
        Ticket ticket = comment.getTicket();

        // Check if the ticket is closed
        if (ticket.getStatus() == Status.CLOSED) {
            throw new IllegalStateException("Cannot delete comment for a closed ticket.");
        }

        // Check permissions
        checkPermissions(authentication, comment);

        // Delete the comment
        commentRepository.delete(comment);
        modelMapper.map(comment, CommentResponseDTO.class);
    }

    @Override
    public CommentListDTO findCommentsByPostId(long id) {
        ticketService.getTicketEntityOrThrow(id);
        return new CommentListDTO(
                commentRepository.findCommentsByTicketId(id).stream()
                        .map(comment -> modelMapper.map(comment, CommentResponseDTO.class))
                        .toList()
        );
    }

    private CommentResponseDTO saveAndMapToResponseDTO(Comment comment) {
        Comment savedComment = commentRepository.save(comment);
        return modelMapper.map(savedComment, CommentResponseDTO.class);
    }

    private User getUserFromAuthentication(Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        // Make sure the claim name here is correct. For example, if it's 'sub' or a custom one:
        String username = jwt.getClaim("sub");
        return userService.findByUsername(username);
    }


    private User checkPermissions(Authentication authentication, Comment comment) {
        User user = getUserFromAuthentication(authentication);
        boolean isAdmin = user.getRoles().stream().anyMatch(role -> role.getName().equalsIgnoreCase("ROLE_ADMIN"));

        if (!isAdmin && !Objects.equals(comment.getUser().getId(), user.getId())) {
            throw new AuthenticationException("User not authorized");
        }
        return user;
    }

    private Comment getCommentEntityOrThrow(long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", id));
    }
}