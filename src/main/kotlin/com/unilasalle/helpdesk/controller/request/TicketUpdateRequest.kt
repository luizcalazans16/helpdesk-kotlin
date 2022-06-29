package com.unilasalle.helpdesk.controller.request

import com.unilasalle.helpdesk.model.Ticket

data class TicketUpdateRequest(
    val description: String? = null,
    val title: String? = null,
    val priority: Ticket.TicketPriority? = null,
    val category: String? = null,
    val response: String? = null
)
