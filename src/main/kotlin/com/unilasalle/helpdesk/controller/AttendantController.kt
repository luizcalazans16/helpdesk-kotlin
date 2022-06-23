package com.unilasalle.helpdesk.controller

import com.unilasalle.helpdesk.service.TicketService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID


@RestController
@RequestMapping("/api/v1/attendants")
class AttendantController(
    private val ticketService: TicketService
) {
    @PostMapping("/{attendantId}/ticket/{ticketId}/")
    fun registerAttendance(@PathVariable attendantId: UUID, @PathVariable ticketId: UUID) {
        ticketService.registerAttendance(attendantId, ticketId)
    }
}