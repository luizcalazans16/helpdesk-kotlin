package com.unilasalle.helpdesk.security

import org.springframework.security.access.prepost.PreAuthorize

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@PreAuthorize("hasRole('ROLE_ADMIN') or #userId == authentication.principal.id")
annotation class UserCanOnlyAccessTheirOwnResource
