package com.unilasalle.helpdesk.controller.request

import com.unilasalle.helpdesk.model.User.UserStatus
import com.unilasalle.helpdesk.validation.EmailAvailable
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class UserUpdateRequest(
    @field:NotEmpty(message = "Nome deve ser informado")
    var name: String,

    @field:Email(message = "E-mail deve ser válido")
    @EmailAvailable
    var email: String,

    var status: UserStatus
)
