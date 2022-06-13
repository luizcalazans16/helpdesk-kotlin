package com.unilasalle.helpdesk.controller

import com.unilasalle.helpdesk.controller.request.UserRegisterRequest
import com.unilasalle.helpdesk.extension.toUserEntity
import com.unilasalle.helpdesk.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/apí/v1/users")
class UserController(
    private val userService: UserService
) {

    @PostMapping
    fun register(@RequestBody request: UserRegisterRequest) {
        userService.registerUser(request.toUserEntity())
    }
}