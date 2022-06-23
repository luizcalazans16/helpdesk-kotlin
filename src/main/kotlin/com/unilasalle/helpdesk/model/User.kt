package com.unilasalle.helpdesk.model

import java.util.UUID
import javax.persistence.CollectionTable
import javax.persistence.Column
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.Table

@Entity(name = "user")
@Table(name = "users")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,

    @Column
    val name: String,

    @Column
    val email: String,

    @Column
    val password: String,

    @Column
    @Enumerated(EnumType.STRING)
    val status: UserStatus,

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @CollectionTable(
        name = "user_roles",
        joinColumns = [JoinColumn(name = "user_id")]
    )
    @ElementCollection(
        targetClass = UserRole::class,
        fetch = FetchType.EAGER
    )
    val roles: Set<UserRole> = setOf()
) {
    enum class UserRole(val description: String) {
        ADMIN("ROLE_ADMIN"),
        USER("ROLE_USER")
    }

    enum class UserStatus {
        ACTIVE,
        INACTIVE
    }
}
