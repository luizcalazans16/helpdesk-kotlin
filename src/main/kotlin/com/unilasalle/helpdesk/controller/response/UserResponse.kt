package com.unilasalle.helpdesk.controller.response

import java.util.*

data class UserResponse(
    val id: UUID? = null,
    val name: String,
    val email: String,
    val status: String
)