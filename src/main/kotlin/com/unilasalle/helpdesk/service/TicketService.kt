package com.unilasalle.helpdesk.service

import com.unilasalle.helpdesk.model.Ticket
import com.unilasalle.helpdesk.repository.TicketRepository
import mu.KLogging
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class TicketService(
    private val ticketRepository: TicketRepository
) {
    companion object : KLogging()

    fun findAll(): List<Ticket> {
        return ticketRepository.findAll().toList()
    }

    fun findById(ticketId: UUID): Ticket {
        return ticketRepository.findById(ticketId).orElseThrow {
            NotFoundException()
        }
    }

    fun register(entity: Ticket) {
        logger.info { "Registering ticket of applicant: ${entity.applicant.email}" }
        ticketRepository.save(entity)
    }

    fun update(ticketToBeSaved: Ticket) {
        ticketRepository.save(ticketToBeSaved)
    }
}
