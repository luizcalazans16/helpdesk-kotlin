package com.unilasalle.helpdesk.helper

import com.unilasalle.helpdesk.model.Category
import com.unilasalle.helpdesk.model.Ticket
import com.unilasalle.helpdesk.model.User
import java.time.LocalDateTime
import java.util.UUID

object TicketMockHelper {

    fun mockTicket() = Ticket(
        id = UUID.randomUUID(),
        title = "Teste",
        description = "Teste",
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now(),
        priority = Ticket.TicketPriority.HIGH,
        applicant = User(
            id = UUID.randomUUID(),
            name = "Mock User",
            email = "teste@teste.com.br",
            password = "12345",
            roles = setOf(User.UserRole.USER),
            status = User.UserStatus.ACTIVE
        ),
        category = Category(
            id = Int.MAX_VALUE,
            name = "TESTE",
            status = Category.CategoryStatus.ACTIVE
        ),
        status = Ticket.TicketStatus.OPEN
    )

}