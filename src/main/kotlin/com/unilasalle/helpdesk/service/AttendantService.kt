package com.unilasalle.helpdesk.service

import mu.KLogging
import org.springframework.stereotype.Service

@Service
class AttendantService(
    private val userService: UserService,
    private val ticketService: TicketService
) {

    companion object : KLogging()

}