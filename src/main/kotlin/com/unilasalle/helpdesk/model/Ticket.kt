package com.unilasalle.helpdesk.model

import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity(name = "ticket")
@Table(name = "ticket")
data class Ticket(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,

    @Column
    val title: String,

    @Column
    val description: String,

    @Enumerated(EnumType.STRING)
    val priority: TicketPriority,

    @Enumerated(EnumType.STRING)
    val status: TicketStatus,

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    val applicant: User,

    @ManyToOne
    @JoinColumn(name = "category_id")
    val category: Category,

    @ManyToOne
    @JoinColumn(name = "attendant_id")
    val attendant: User? = null,

    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now()
) {
    enum class TicketPriority {
        LOW,
        MEDIUM,
        HIGH
    }
    enum class TicketStatus {
        OPEN,
        IN_PROGRESS,
        CANCELLED,
        CLOSED
    }
}
