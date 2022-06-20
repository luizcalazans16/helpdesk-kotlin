package com.unilasalle.helpdesk.model

import com.unilasalle.helpdesk.enums.TicketPriority
import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.*

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

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    val applicant: User,

    @ManyToOne
    @JoinColumn(name = "attendant_id")
    val attendant: User? = null,

    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now(),

    @OneToMany
    val attachments: Attachment
)
