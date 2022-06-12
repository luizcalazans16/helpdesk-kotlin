package com.unilasalle.helpdesk.controller

import com.unilasalle.helpdesk.service.TicketService
import com.unilasalle.helpdesk.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/attendants")
class AttendantController(
    private val userService: UserService,
    private val ticketService: TicketService
) {

    @PostMapping("/{attendantId}/ticket/{ticketId}/")
    fun registerAttendance() {

    }
}