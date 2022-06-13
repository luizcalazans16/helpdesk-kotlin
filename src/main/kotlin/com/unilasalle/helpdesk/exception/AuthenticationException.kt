package com.unilasalle.helpdesk.exception

class AuthenticationException(
    override val message: String,
    val errorCode: String
) : Exception()