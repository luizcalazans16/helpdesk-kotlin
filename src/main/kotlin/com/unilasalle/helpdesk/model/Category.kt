package com.unilasalle.helpdesk.model

import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity(name = "category")
@Table(name = "category")
data class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: UUID? = null,

    @Column
    val name: String,

    val status: CategoryStatus
) {
    enum class CategoryStatus {
        ACTIVE,
        INACTIVE
    }
}
