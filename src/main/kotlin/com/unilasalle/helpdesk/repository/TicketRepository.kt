package com.unilasalle.helpdesk.repository

import com.unilasalle.helpdesk.model.Ticket
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface TicketRepository : JpaRepository<Ticket, UUID> {
}