package com.unilasalle.helpdesk.controller

import com.unilasalle.helpdesk.controller.request.CategoryRegisterRequest
import com.unilasalle.helpdesk.controller.request.CategoryUpdateRequest
import com.unilasalle.helpdesk.controller.response.CategoryResponse
import com.unilasalle.helpdesk.extension.toCategoryRequest
import com.unilasalle.helpdesk.extension.toCategoryResponse
import com.unilasalle.helpdesk.service.CategoryService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/categories")
class CategoryController(
    private val categoryService: CategoryService
) {
    @GetMapping
    fun findAll(): List<CategoryResponse> {
        return categoryService.findAll().map { it.toCategoryResponse() }
    }
    @PostMapping
    fun registerCategory(@RequestBody request: CategoryRegisterRequest) {
        return categoryService.register(request.toCategoryRequest())
    }
    @PutMapping("/{categoryId}")
    fun updateCategory(@PathVariable categoryId: Int, @RequestBody request: CategoryUpdateRequest) {
        categoryService.update(categoryId, request)
    }
    @PutMapping("/{categoryId}/activate")
    fun activateCategory(@PathVariable categoryId: Int) {
        categoryService.activate(categoryId)
    }

    @DeleteMapping("/{categoryId}/inactivate")
    fun inactivateCategory(@PathVariable categoryId: Int) {
        categoryService.inactivate(categoryId)
    }

}