package com.unilasalle.helpdesk.service

import com.unilasalle.helpdesk.exception.NotFoundException
import com.unilasalle.helpdesk.model.Category
import com.unilasalle.helpdesk.repository.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository
) {
    fun findAll(): List<Category> {
        return categoryRepository.findAll().toList().filter {
            it.status == Category.CategoryStatus.ACTIVE
        }
    }

    fun findById(id: Int): Category {
        return categoryRepository.findById(id).orElseThrow {
            NotFoundException("Category $id not found", "Not found")
        }
    }

    fun register(entity: Category) {
        categoryRepository.save(entity)
    }
}
