package com.unilasalle.helpdesk.controller

import com.unilasalle.helpdesk.controller.request.TicketRegisterRequest
import com.unilasalle.helpdesk.controller.request.TicketUpdateRequest
import com.unilasalle.helpdesk.controller.response.PageResponse
import com.unilasalle.helpdesk.controller.response.TicketResponse
import com.unilasalle.helpdesk.extension.toPageResponse
import com.unilasalle.helpdesk.extension.toTicketResponse
import com.unilasalle.helpdesk.service.TicketService
import mu.KLogging
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
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
    private val ticketService: TicketService
) {
    companion object : KLogging()

    @GetMapping
    fun findAll(@PageableDefault(page = 0, size = 10) pageable: Pageable): PageResponse<TicketResponse> {
        logger.info { "Finding all tickets" }
        return ticketService.findAll(pageable).map { it.toTicketResponse() }.toPageResponse()
    }

    @GetMapping("/{ticketId}")
    fun findById(@PathVariable ticketId: UUID): TicketResponse {
        return ticketService.findById(ticketId).toTicketResponse()
    }
    @GetMapping("/applicant/{applicantId}")
    fun findByApplicantId(@PathVariable applicantId: UUID): List<TicketResponse> {
        return ticketService.findByApplicantId(applicantId).map { it.toTicketResponse() }
    }
    @PostMapping
    fun registerTicket(@RequestBody request: TicketRegisterRequest) {
        logger.info { "Sending request [$request] to register ticket" }
        ticketService.register(request)
    }
    @PutMapping("/{ticketId}")
    fun updateTicket(@PathVariable ticketId: UUID, @RequestBody updateRequest: TicketUpdateRequest) {
        ticketService.update(ticketId, updateRequest)
    }
}
