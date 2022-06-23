package com.unilasalle.helpdesk.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity(name = "category")
@Table(name = "categories")
data class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @Column
    val name: String,

    @Enumerated(EnumType.STRING)
    val status: CategoryStatus
) {
    enum class CategoryStatus {
        ACTIVE,
        INACTIVE
    }
}
