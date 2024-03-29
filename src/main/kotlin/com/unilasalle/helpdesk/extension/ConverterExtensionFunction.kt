package com.unilasalle.helpdesk.extension

import com.unilasalle.helpdesk.controller.request.CategoryRegisterRequest
import com.unilasalle.helpdesk.controller.request.TicketRegisterRequest
import com.unilasalle.helpdesk.controller.request.TicketUpdateRequest
import com.unilasalle.helpdesk.controller.request.UserRegisterRequest
import com.unilasalle.helpdesk.controller.request.UserUpdateRequest
import com.unilasalle.helpdesk.controller.response.AttachmentResponse
import com.unilasalle.helpdesk.controller.response.CategoryResponse
import com.unilasalle.helpdesk.controller.response.PageResponse
import com.unilasalle.helpdesk.controller.response.TicketResponse
import com.unilasalle.helpdesk.controller.response.UserResponse
import com.unilasalle.helpdesk.model.Category
import com.unilasalle.helpdesk.model.Category.CategoryStatus
import com.unilasalle.helpdesk.model.Ticket
import com.unilasalle.helpdesk.model.Ticket.TicketPriority
import com.unilasalle.helpdesk.model.User
import com.unilasalle.helpdesk.model.User.UserStatus
import org.springframework.data.domain.Page
import org.springframework.util.Base64Utils


fun Category.toCategoryResponse(): CategoryResponse {
    return CategoryResponse(
        id = this.id!!,
        name = this.name
    )
}

fun TicketRegisterRequest.toTicketEntity(
    applicant: User,
    category: Category
): Ticket {
    return Ticket(
        title = this.title,
        description = this.description,
        priority = TicketPriority.valueOf(this.priority),
        applicant = applicant,
        category = category,
        status = Ticket.TicketStatus.OPEN
    )
}

fun TicketUpdateRequest.toTicketEntity(previousValue: Ticket): Ticket {
    return Ticket(
        id = previousValue.id,
        title = previousValue.title,
        description = this.description ?: previousValue.description,
        priority = this.priority ?: previousValue.priority,
        applicant = previousValue.applicant,
        createdAt = previousValue.createdAt,
        updatedAt = previousValue.updatedAt,
        category = Category(
            name = this.category ?: previousValue.category.name,
            status = previousValue.category.status
        ),
        status = previousValue.status,
        response = this.response
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
        updatedAt = this.updatedAt,
        category = this.category.name,
        status = this.status.name,
        applicantName = this.applicant.name,
        attendantName = this.attendant?.name,
        response = this.response,
        attachments = this.attachments?.map {
            AttachmentResponse(
                name = it.name,
                data = Base64Utils.encodeToString(it.fileData)
            )
        }
    )
}

fun UserRegisterRequest.toUserEntity(): User {
    return User(
        name = this.name,
        email = this.email,
        password = this.password,
        status = UserStatus.ACTIVE,
        roles = this.roles
    )
}


fun User.toUserResponse(): UserResponse {
    return UserResponse(
        id = this.id,
        name = this.name,
        email = this.email,
        status = this.status.name
    )
}


fun UserUpdateRequest.toUserEntity(previousValue: User): User {
    return User(
        id = previousValue.id,
        name = this.name,
        email = this.email,
        status = previousValue.status,
        password = previousValue.password
    )
}

fun CategoryRegisterRequest.toCategoryRequest(): Category {
    return Category(
        name = this.name,
        status = CategoryStatus.ACTIVE
    )
}

fun <T> Page<T>.toPageResponse(): PageResponse<T> {
    return PageResponse(
        this.content,
        this.number,
        this.totalElements,
        this.totalPages
    )
}