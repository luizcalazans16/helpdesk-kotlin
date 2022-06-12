package com.unilasalle.helpdesk.controller.response

import com.unilasalle.helpdesk.enums.TicketPriority
import java.time.LocalDateTime
import java.util.UUID

data class TicketResponse(
    val id: UUID? = null,
    val title: String,
    val description: String,
    val priority: TicketPriority,
    val applicantId: UUID,
    val attendantId: UUID? = null,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
