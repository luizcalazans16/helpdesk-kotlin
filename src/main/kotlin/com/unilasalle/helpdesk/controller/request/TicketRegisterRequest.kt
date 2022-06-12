package com.unilasalle.helpdesk.controller.request

import java.util.UUID

data class TicketRegisterRequest(
    val title: String,
    val applicantId: UUID,
    val description: String,
    val priority: String
)