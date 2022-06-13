package com.unilasalle.helpdesk.extension

import com.unilasalle.helpdesk.controller.request.TicketRegisterRequest
import com.unilasalle.helpdesk.controller.request.UserRegisterRequest
import com.unilasalle.helpdesk.controller.response.CategoryResponse
import com.unilasalle.helpdesk.controller.response.TicketResponse
import com.unilasalle.helpdesk.enums.TicketPriority
import com.unilasalle.helpdesk.enums.UserStatus
import com.unilasalle.helpdesk.model.Category
import com.unilasalle.helpdesk.model.Ticket
import com.unilasalle.helpdesk.model.User



fun Category.toCategoryResponse(): CategoryResponse {
    return CategoryResponse(
        id = this.id!!,
        name = this.name
    )
}

fun TicketRegisterRequest.toTicketEntity(applicant: User): Ticket {
    return Ticket(
        title = this.title,
        description = this.description,
        priority = TicketPriority.valueOf(this.priority),
        applicant = applicant
    )
}

fun Ticket.toTicketResponse(): TicketResponse {
    return TicketResponse(
        id = this.id,
        title = this.title,
        description = this.description,
        applicantId = this.applicant.id!!,
        attendantId = this.attendant?.id,
        priority = this.priority,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}

fun UserRegisterRequest.toUserEntity(): User {
    return User(
        name = this.name,
        email = this.email,
        password = this.password,
        status = UserStatus.ACTIVE
    )
}