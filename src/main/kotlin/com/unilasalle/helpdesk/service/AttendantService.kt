package com.unilasalle.helpdesk.service

import mu.KLogging
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class AttendantService(
    private val userService: UserService,
    private val ticketService: TicketService
) {

    companion object : KLogging()

    fun registerAttendance(attendantId: UUID, ticketId: UUID) {
        logger.info { "Registering attendance for ticket: [$ticketId] | attendant: [$attendantId]" }
        val foundAttendant = userService.findById(attendantId)
        val ticket = ticketService.findById(ticketId)

        val ticketToBeSaved = ticket.copy(
            attendant = foundAttendant
        )
        ticketService.update(ticketToBeSaved)
    }
}