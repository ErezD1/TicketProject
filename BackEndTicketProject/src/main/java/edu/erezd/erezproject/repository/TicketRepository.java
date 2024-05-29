package edu.erezd.erezproject.repository;

import edu.erezd.erezproject.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
