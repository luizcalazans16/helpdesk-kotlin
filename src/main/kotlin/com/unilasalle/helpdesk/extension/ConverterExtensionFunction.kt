package com.unilasalle.helpdesk.extension

import com.unilasalle.helpdesk.controller.request.TicketRegisterRequest
import com.unilasalle.helpdesk.controller.response.TicketResponse
import com.unilasalle.helpdesk.enums.TicketPriority
import com.unilasalle.helpdesk.model.Ticket
import com.unilasalle.helpdesk.model.User
import java.util.UUID

fun TicketRegisterRequest.toTicketEntity(applicant: User): Ticket {
    return Ticket(
        title = this.title,
        description = this.description,
        priority = TicketPriority.valueOf(this.priority),
        applicant = applicant
    )
}

fun Ticket.toTicketResponse(): TicketResponse {
    return TicketResponse(
        id = this.id,
        title = this.title,
        description = this.description,
        applicantId = this.applicant.id!!,
        attendantId = this.attendant?.id,
        priority = this.priority,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}