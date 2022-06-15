package com.unilasalle.helpdesk.controller

import com.unilasalle.helpdesk.controller.request.TicketRegisterRequest
import com.unilasalle.helpdesk.controller.request.TicketUpdateRequest
import com.unilasalle.helpdesk.controller.response.TicketResponse
import com.unilasalle.helpdesk.extension.toTicketEntity
import com.unilasalle.helpdesk.extension.toTicketResponse
import com.unilasalle.helpdesk.service.TicketService
import com.unilasalle.helpdesk.service.UserService
import mu.KLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID


@RestController
@RequestMapping("/api/v1/tickets")
class TicketController(
    private val ticketService: TicketService,
    private val userService: UserService
) {
    companion object : KLogging()

    @GetMapping
    fun findAll(): List<TicketResponse> {
        logger.info { "Finding all tickets" }
        return ticketService.findAll().map { it.toTicketResponse() }
    }

    @GetMapping("/{ticketId}")
    fun findById(@PathVariable ticketId: UUID): TicketResponse {
        return ticketService.findById(ticketId).toTicketResponse()
    }

    @PostMapping
    fun registerTicket(@RequestBody request: TicketRegisterRequest) {
        logger.info { "Sending request [$request] to register ticket" }
        val user = userService.findById(request.applicantId)
        ticketService.register(request.toTicketEntity(user))

    }

    @PutMapping("/{ticketId}")
    fun updateTicket(@PathVariable ticketId: UUID, @RequestBody updateRequest: TicketUpdateRequest) {
        ticketService.update(ticketId, updateRequest)
    }
}