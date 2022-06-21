package com.unilasalle.helpdesk.controller.response

import java.util.UUID

data class CategoryResponse(
    val id: UUID,
    val name: String
)