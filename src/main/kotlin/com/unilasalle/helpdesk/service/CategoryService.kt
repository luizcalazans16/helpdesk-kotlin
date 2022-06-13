package com.unilasalle.helpdesk.service

import com.unilasalle.helpdesk.model.Category
import com.unilasalle.helpdesk.repository.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository
) {
    fun findAll(): List<Category> {
        return categoryRepository.findAll().toList()
    }
}