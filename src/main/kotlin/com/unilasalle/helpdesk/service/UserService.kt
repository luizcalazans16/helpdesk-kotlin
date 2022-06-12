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
    fun findById(applicantId: UUID): User {
        return userRepository.findById(applicantId).orElseThrow {
            NotFoundException("User $applicantId not found", "Not found")
        }
    }
}