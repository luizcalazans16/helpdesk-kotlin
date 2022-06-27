package com.unilasalle.helpdesk.exception

class EmailAlreadyRegisteredException(
    override val message: String,
    val errorCode: String
) : Exception() {
}