package com.sabaka.journal.controller

import com.sabaka.journal.model.Category
import com.sabaka.journal.service.CategoryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/categories")
class CategoryController(private val categoryService: CategoryService) {

    @GetMapping
    fun getAllCategories(): List<Category> = categoryService.getAllCategories()

    @GetMapping("/{id}")
    fun getCategoryById(@PathVariable id: Long): ResponseEntity<Category> {
        val category = categoryService.getCategoryById(id)
        return if (category != null) ResponseEntity.ok(category) else ResponseEntity.notFound().build()
    }

    @PostMapping
    fun createCategory(@RequestBody category: Category): Category = categoryService.createCategory(category)

    @PutMapping("/{id}")
    fun updateCategory(@PathVariable id: Long, @RequestBody category: Category): ResponseEntity<Category> {
        val updatedCategory = categoryService.updateCategory(id, category)
        return if (updatedCategory != null) ResponseEntity.ok(updatedCategory) else ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{id}")
    fun deleteCategory(@PathVariable id: Long): ResponseEntity<Void> {
        categoryService.deleteCategory(id)
        return ResponseEntity.noContent().build()
    }
}
