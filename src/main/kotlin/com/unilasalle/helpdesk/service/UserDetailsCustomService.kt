package com.unilasalle.helpdesk.service

import com.unilasalle.helpdesk.exception.AuthenticationException
import com.unilasalle.helpdesk.repository.UserRepository
import com.unilasalle.helpdesk.security.UserCustomDetails
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserDetailsCustomService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(id: String): UserDetails {
        val user = userRepository.findById(UUID.fromString(id)).orElseThrow {
            AuthenticationException("User not found!", "999")
        }
        return UserCustomDetails(user)
    }
}