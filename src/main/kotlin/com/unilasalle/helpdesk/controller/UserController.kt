package com.unilasalle.helpdesk.controller

import com.unilasalle.helpdesk.controller.request.UserRegisterRequest
import com.unilasalle.helpdesk.controller.request.UserUpdateRequest
import com.unilasalle.helpdesk.controller.response.UserResponse
import com.unilasalle.helpdesk.extension.toUserEntity
import com.unilasalle.helpdesk.extension.toUserResponse
import com.unilasalle.helpdesk.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val userService: UserService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findAll(): List<UserResponse> {
        return userService.findAll().map { it.toUserResponse() }
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    fun findById(@PathVariable userId: UUID): UserResponse {
        return userService.findById(userId).toUserResponse()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun register(@RequestBody request: UserRegisterRequest) {
        userService.registerUser(request.toUserEntity())
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable userId: UUID, @RequestBody request: UserUpdateRequest) {
        userService.updateUser(userId, request)
    }
}