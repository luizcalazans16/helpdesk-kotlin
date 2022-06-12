package com.unilasalle.helpdesk.service

import com.unilasalle.helpdesk.model.Ticket
import com.unilasalle.helpdesk.repository.TicketRepository
import mu.KLogging
import org.springframework.stereotype.Service

@Service
class TicketService(
    private val ticketRepository: TicketRepository
) {
    companion object : KLogging()

    fun findAll(): List<Ticket> {
        return ticketRepository.findAll().toList()
    }

    fun register(entity: Ticket) {
        logger.info { "Registering ticket of applicant: ${entity.applicant.email}" }
        ticketRepository.save(entity)
    }
}
