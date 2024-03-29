package com.unilasalle.helpdesk.service

import com.unilasalle.helpdesk.controller.request.ServiceRegisterRequest
import com.unilasalle.helpdesk.controller.request.TicketRegisterRequest
import com.unilasalle.helpdesk.controller.request.TicketRequestFilter
import com.unilasalle.helpdesk.controller.request.TicketUpdateRequest
import com.unilasalle.helpdesk.enums.Errors
import com.unilasalle.helpdesk.exception.NotFoundException
import com.unilasalle.helpdesk.extension.toTicketEntity
import com.unilasalle.helpdesk.model.Attachment
import com.unilasalle.helpdesk.model.Ticket
import com.unilasalle.helpdesk.repository.TicketRepository
import com.unilasalle.helpdesk.repository.custom.TicketSpecification
import mu.KLogging
import org.apache.tomcat.util.codec.binary.Base64
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.util.UUID


@Service
class TicketService(
    private val userService: UserService,
    private val categoryService: CategoryService,
    private val ticketRepository: TicketRepository
) {
    companion object : KLogging()

    fun findAll(pageable: Pageable): Page<Ticket> {
        return ticketRepository.findAll(pageable)
    }

    @Transactional
    fun findById(ticketId: UUID): Ticket {
        return ticketRepository.findById(ticketId).orElseThrow {
            throw NotFoundException(Errors.HD201.message.format(ticketId), Errors.HD201.code)
        }
    }

    fun register(request: TicketRegisterRequest, attachment: MultipartFile? = null) {

        val applicant = userService.findById(request.applicantId)
        val category = categoryService.findById(request.categoryId)

        val entity = request.toTicketEntity(applicant = applicant, category = category)
        logger.info { "Registering ticket of applicant: ${entity.applicant.email}" }


        if (attachment != null) {
            ticketRepository.save(
                entity.copy(
                    attachments = listOf(
                        Attachment(
                            name = attachment.name,
                            fileData = Base64.encodeBase64(attachment.bytes)
                        )
                    )
                )
            )
        } else {
            ticketRepository.save(entity)
        }

    }

    fun update(ticketId: UUID, ticketToBeSaved: TicketUpdateRequest) {
        val storedTicket = findById(ticketId)

        ticketRepository.save(
            ticketToBeSaved.toTicketEntity(storedTicket)
        )
    }

    fun registerService(attendantId: UUID, ticketId: UUID, registerRequest: ServiceRegisterRequest) {
        logger.info { "Registering service response for ticket: [$ticketId] | attendant: [$attendantId]" }
        val foundAttendant = userService.findById(attendantId)
        val ticket = findById(ticketId)

        val ticketToBeSaved = ticket.copy(
            attendant = foundAttendant,
            status = Ticket.TicketStatus.IN_PROGRESS,
            response = registerRequest.response
        )
        ticketRepository.save(ticketToBeSaved)
    }

    fun findByApplicantId(applicantId: UUID): List<Ticket> {
        return ticketRepository.findByApplicantId(applicantId)
    }

    fun closeTicket(attendantId: UUID, ticketId: UUID) {
        val ticket = findById(ticketId)

        val ticketToBeSaved = ticket.copy(
            status = Ticket.TicketStatus.CLOSED
        )

        ticketRepository.save(ticketToBeSaved)
    }

    fun findByFilter(ticketRequestFilter: TicketRequestFilter, pageable: Pageable): Page<Ticket> {
        val spec = TicketSpecification(ticketRequestFilter)
        return ticketRepository.findAll(spec, pageable)
    }
}
