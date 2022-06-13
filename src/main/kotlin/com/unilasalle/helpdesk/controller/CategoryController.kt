package com.unilasalle.helpdesk.controller

import com.unilasalle.helpdesk.controller.response.CategoryResponse
import com.unilasalle.helpdesk.extension.toCategoryResponse
import com.unilasalle.helpdesk.service.CategoryService
import org.springframework.web.bind.annotation.GetMapping
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

}