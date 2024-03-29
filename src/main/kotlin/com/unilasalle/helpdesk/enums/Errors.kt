package com.unilasalle.helpdesk.enums

enum class Errors(
    val code: String,
    val message: String
) {

    HD000("HD-000", "Access Denied"),
    HD201("HD-201", "Ticket [%s] not found"),
    HD202("HD-202", "User [%s] not found"),
    HD301("HD-301", "E-mail [%s] is already registered"),
}