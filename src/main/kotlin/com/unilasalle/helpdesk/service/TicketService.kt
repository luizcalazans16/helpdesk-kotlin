package com.unilasalle.helpdesk.service

import com.unilasalle.helpdesk.controller.request.TicketRegisterRequest
import com.unilasalle.helpdesk.controller.request.TicketUpdateRequest
import com.unilasalle.helpdesk.enums.Errors
import com.unilasalle.helpdesk.exception.NotFoundException
import com.unilasalle.helpdesk.extension.toTicketEntity
import com.unilasalle.helpdesk.model.Ticket
import com.unilasalle.helpdesk.repository.TicketRepository
import mu.KLogging
import org.springframework.stereotype.Service
import java.util.UUID


@Service
class TicketService(
    private val userService: UserService,
    private val categoryService: CategoryService,
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

    fun register(request: TicketRegisterRequest) {
        val applicant = userService.findById(request.applicantId)
        val category = categoryService.findById(request.categoryId)

        val entity = request.toTicketEntity(applicant = applicant, category = category)

        logger.info { "Registering ticket of applicant: ${entity.applicant.email}" }
        ticketRepository.save(entity)
    }

    fun update(ticketId: UUID, ticketToBeSaved: TicketUpdateRequest) {
        val storedTicket = findById(ticketId)

        ticketRepository.save(
            ticketToBeSaved.toTicketEntity(storedTicket)
        )
    }

    fun registerAttendance(attendantId: UUID, ticketId: UUID) {
        logger.info { "Registering attendance for ticket: [$ticketId] | attendant: [$attendantId]" }
        val foundAttendant = userService.findById(attendantId)
        val ticket = findById(ticketId)

        val ticketToBeSaved = ticket.copy(
            attendant = foundAttendant
        )
        ticketRepository.save(ticketToBeSaved)
    }

    fun findByApplicantId(applicantId: UUID): List<Ticket> {
        return ticketRepository.findByApplicantId(applicantId)
    }
}
