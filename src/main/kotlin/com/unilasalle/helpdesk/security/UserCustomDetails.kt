package com.unilasalle.helpdesk.security

import com.unilasalle.helpdesk.model.User.UserStatus
import com.unilasalle.helpdesk.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.UUID

class UserCustomDetails(
    val user: User
) : UserDetails {

    val id: UUID = user.id!!

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return user.roles.map {
            SimpleGrantedAuthority(it.description)
        }.toMutableList()

    }

    override fun getPassword(): String = user.password

    override fun getUsername(): String = user.id.toString()

    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = user.status == UserStatus.ACTIVE


}