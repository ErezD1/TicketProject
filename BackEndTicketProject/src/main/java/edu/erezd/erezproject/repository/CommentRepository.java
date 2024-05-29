package edu.erezd.erezproject.repository;


import edu.erezd.erezproject.entity.Comment;
import edu.erezd.erezproject.entity.Ticket;
import edu.erezd.erezproject.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByTicket(Ticket post, Pageable pageable);

    //JPA Derived Query methods:
    List<Comment> findCommentsByTicketId(long postId);

    List<Comment> findCommentsByUser(User user);

    @Query("SELECT c FROM Comment c JOIN c.user u JOIN u.roles r WHERE c.ticket.id = :ticketId AND r.name = 'ROLE_ADMIN' ORDER BY c.createdAt DESC")
    Comment findLatestAdminCommentByTicketId(@Param("ticketId") long ticketId);

}
