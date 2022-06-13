package com.unilasalle.helpdesk.controller

import com.unilasalle.helpdesk.service.AttendantService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID


@RestController
@RequestMapping("/api/v1/attendants")
class AttendantController(
    private val attendantService: AttendantService
) {

    @PostMapping("/{attendantId}/ticket/{ticketId}/")
    fun registerAttendance(@PathVariable attendantId: UUID, @PathVariable ticketId: UUID) {
        attendantService.registerAttendance(attendantId, ticketId)
    }
}