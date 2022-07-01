package com.unilasalle.helpdesk.service

import com.unilasalle.helpdesk.controller.request.CategoryUpdateRequest
import com.unilasalle.helpdesk.exception.NotFoundException
import com.unilasalle.helpdesk.model.Category
import com.unilasalle.helpdesk.repository.CategoryRepository
import mu.KLogging
import org.springframework.stereotype.Service

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository
) {
    companion object : KLogging()

    fun findAll(): List<Category> {
        logger.info { "Finding all categories" }
        return categoryRepository.findAll().toList().filter {
            it.status == Category.CategoryStatus.ACTIVE
        }
    }

    fun findById(id: Int): Category {
        logger.info { "Finding category [$id]" }
        return categoryRepository.findById(id).orElseThrow {
            NotFoundException("Category $id not found", "Not found")
        }
    }

    fun register(entity: Category) {
        logger.info { "Registering category [$entity]" }
        categoryRepository.save(entity)
    }

    fun update(categoryId: Int, request: CategoryUpdateRequest) {
        logger.info { "Updating category [$categoryId] | request: [$request]" }
    }

    fun activate(categoryId: Int) {
        logger.info { "Activating category [$categoryId] " }
        val storedCategory = findById(categoryId)

        categoryRepository.save(
            storedCategory.copy(
                status = Category.CategoryStatus.ACTIVE
            )
        )
    }

    fun inactivate(categoryId: Int) {
        logger.info { "Inactivating category [$categoryId] " }
        val storedCategory = findById(categoryId)

        categoryRepository.save(
            storedCategory.copy(
                status = Category.CategoryStatus.INACTIVE
            )
        )
    }
}
