package com.unilasalle.helpdesk.exception

class NotFoundException(
    override val message: String,
    val errorCode: String
) : Exception() {
}