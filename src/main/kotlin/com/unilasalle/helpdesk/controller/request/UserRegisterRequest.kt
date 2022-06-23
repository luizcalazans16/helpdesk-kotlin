package com.unilasalle.helpdesk.controller.request

import com.unilasalle.helpdesk.model.User.UserRole
import com.unilasalle.helpdesk.validation.EmailAvailable
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class UserRegisterRequest(
    @field:NotEmpty(message = "Nome deve ser informado")
    var name: String,

    @field:Email(message = "E-mail deve ser valido")
    @EmailAvailable
    var email: String,

    @field:NotEmpty(message = "Senha deve ser informada")
    var password: String,

    @field:NotEmpty(message = "O cargo deve ser informado")
    var roles: Set<UserRole>
)