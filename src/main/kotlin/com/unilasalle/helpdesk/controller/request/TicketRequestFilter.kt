package com.unilasalle.helpdesk.controller.request

import com.unilasalle.helpdesk.model.Ticket.TicketStatus

data class TicketRequestFilter(
    val status: TicketStatus? = null,
    val description: String? = null
)