package com.unilasalle.helpdesk.repository.custom

import com.unilasalle.helpdesk.controller.request.TicketRequestFilter
import com.unilasalle.helpdesk.model.Ticket
import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

class TicketSpecification(
    private val filter: TicketRequestFilter
): Specification<Ticket> {

    override fun toPredicate(
        root: Root<Ticket>,
        query: CriteriaQuery<*>,
        criteriaBuilder: CriteriaBuilder
    ): Predicate? {
        val predicates: MutableList<Predicate> = mutableListOf()

        if (filter.status != null) {
            predicates.add(criteriaBuilder.equal(root.get<String>("status"), filter.status))
        }
        if (filter.description != null) {
            predicates.add(criteriaBuilder.equal(root.get<String>("description"), filter.description))
        }

        return criteriaBuilder.and(*predicates.toTypedArray())
    }
}