package com.unilasalle.helpdesk.service

import com.unilasalle.helpdesk.helper.TicketMockHelper
import com.unilasalle.helpdesk.repository.TicketRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.Optional

@ExtendWith(MockKExtension::class)
class TicketServiceTest {

    @InjectMockKs
    private lateinit var ticketService: TicketService

    @MockK
    private lateinit var ticketRepository: TicketRepository

    @MockK
    private lateinit var userService: UserService

    @MockK
    private lateinit var categoryService: CategoryService

    @Test
    fun `should return a ticket`() {
        val mockTicket = TicketMockHelper.mockTicket()

        every {
            ticketRepository.findById(mockTicket.id!!)
        } returns Optional.of(mockTicket)

        val result = ticketService.findById(mockTicket.id!!)

        Assertions.assertThat(result).isNotNull
        verify(exactly = 1) {
            ticketRepository.findById(mockTicket.id!!)
        }

    }
}