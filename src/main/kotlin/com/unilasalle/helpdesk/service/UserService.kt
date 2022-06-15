package com.unilasalle.helpdesk.service

import com.unilasalle.helpdesk.controller.request.UserUpdateRequest
import com.unilasalle.helpdesk.enums.Errors
import com.unilasalle.helpdesk.exception.NotFoundException
import com.unilasalle.helpdesk.extension.toUserEntity
import com.unilasalle.helpdesk.model.User
import com.unilasalle.helpdesk.repository.UserRepository
import mu.KLogging
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserService(
    private val passwordEncoder: BCryptPasswordEncoder,
    private val userRepository: UserRepository
) {

    companion object : KLogging()

    fun findById(userId: UUID): User {
        logger.info { "Finding user by id: [$userId]" }
        return userRepository.findById(userId).orElseThrow {
            NotFoundException("User $userId not found", "Not found")
        }
    }

    fun findAll(): List<User> {
        logger.info { "Listing users" }
        return userRepository.findAll().toList()
    }

    fun registerUser(entity: User) {
        logger.info { "Registering user: [$entity]" }
        val userToSave = entity.copy(
            password = passwordEncoder.encode(entity.password)
        )
        userRepository.save(userToSave)
    }

    fun updateUser(userId: UUID, entity: UserUpdateRequest) {
        var user = findById(userId)
        user = entity.toUserEntity(user)
        if(userRepository.existsById(userId)) {
            throw NotFoundException(Errors.HD000.message.format(userId), Errors.HD000.code)
        }
        userRepository.save(user)
    }
}