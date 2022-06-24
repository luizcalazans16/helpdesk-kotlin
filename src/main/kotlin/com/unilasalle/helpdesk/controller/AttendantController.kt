package com.unilasalle.helpdesk.controller

import com.unilasalle.helpdesk.service.TicketService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID


@RestController
@RequestMapping("/api/v1/attendants")
class AttendantController(
    private val ticketService: TicketService
) {
    @PostMapping("/{attendantId}/ticket/{ticketId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun registerAttendance(@PathVariable attendantId: UUID, @PathVariable ticketId: UUID) {
        ticketService.registerAttendance(attendantId, ticketId)
    }

    @PatchMapping("/{attendantId}/ticket/{ticketId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun closeTicket(@PathVariable attendantId: UUID, @PathVariable ticketId: UUID) {
        ticketService.closeTicket(attendantId, ticketId)
    }
}