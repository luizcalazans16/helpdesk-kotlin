package com.unilasalle.helpdesk.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "attachment")
data class Attachment(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID,

    val name: String,

    @Lob
    val fileData: ByteArray
)
