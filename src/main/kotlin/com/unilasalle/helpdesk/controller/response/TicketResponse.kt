package com.unilasalle.helpdesk.controller.response

import com.unilasalle.helpdesk.model.Ticket.TicketPriority
import java.time.LocalDateTime
import java.util.UUID

data class TicketResponse(
    val id: UUID? = null,
    val title: String,
    val description: String,
    val priority: TicketPriority,
    val applicantId: UUID,
    val applicantName: String,
    val attendantId: UUID? = null,
    val attendantName: String? = null,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val category: String,
    val status: String,
    val response: String? = null,
    val attachments: List<AttachmentResponse>? = null
)
