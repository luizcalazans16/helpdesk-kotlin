package com.unilasalle.helpdesk.enums

enum class Errors(
    val code: String,
    val message: String
) {

    HD000("HD-000", "Access Denied"),
    HD201("HD-201", "Ticket [%s] not found"),
}