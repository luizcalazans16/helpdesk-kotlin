package com.unilasalle.helpdesk.service

import com.unilasalle.helpdesk.enums.Errors
import com.unilasalle.helpdesk.exception.NotFoundException
import com.unilasalle.helpdesk.model.Ticket
import com.unilasalle.helpdesk.repository.TicketRepository
import mu.KLogging
import org.springframework.stereotype.Service
import java.util.*

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
            throw NotFoundException(Errors.HD201.message.format(ticketId), Errors.HD201.code)
        }
    }

    fun register(entity: Ticket) {
        logger.info { "Registering ticket of applicant: ${entity.applicant.email}" }
        ticketRepository.save(entity)
    }

    fun update(ticketToBeSaved: Ticket) {
        if (!ticketRepository.existsById(ticketToBeSaved.id!!)) {
            throw NotFoundException(Errors.HD201.message.format(ticketToBeSaved.id), Errors.HD201.code)
        }
        ticketRepository.save(ticketToBeSaved)
    }

}
