package com.unilasalle.helpdesk.service

import com.unilasalle.helpdesk.exception.NotFoundException
import com.unilasalle.helpdesk.model.User
import com.unilasalle.helpdesk.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserService(
    private val passwordEncoder: BCryptPasswordEncoder,
    private val userRepository: UserRepository
) {
    fun findById(userId: UUID): User {
        return userRepository.findById(userId).orElseThrow {
            NotFoundException("User $userId not found", "Not found")
        }
    }

    fun registerUser(entity: User) {
        val userToSave = entity.copy(
            password = passwordEncoder.encode(entity.password)
        )
        userRepository.save(userToSave)
    }
}