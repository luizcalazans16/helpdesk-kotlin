package com.unilasalle.helpdesk.validation

import javax.validation.Constraint

@Constraint(validatedBy = [EmailAvailableValidator::class])
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class EmailAvailable {
}