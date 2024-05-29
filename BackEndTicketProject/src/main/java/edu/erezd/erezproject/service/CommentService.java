package edu.erezd.erezproject.service;

import edu.erezd.erezproject.dto.comment.CommentListDTO;
import edu.erezd.erezproject.dto.comment.CommentRequestDTO;
import edu.erezd.erezproject.dto.comment.CommentResponseDTO;
import org.springframework.security.core.Authentication;

public interface CommentService {
    CommentResponseDTO createComment(long ticketId, CommentRequestDTO dto, Authentication authentication);

    CommentListDTO findCommentsByPostId(long id);

    CommentResponseDTO updateCommentById(long id, CommentRequestDTO dto, Authentication authentication);

    void deleteCommentById(long id, Authentication authentication);
}
