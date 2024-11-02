package com.sabaka.journal.service

import com.sabaka.journal.model.Category
import com.sabaka.journal.repository.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(private val categoryRepository: CategoryRepository) {
    fun getAllCategories(): List<Category> = categoryRepository.findAll()

    fun getCategoryById(id: Long): Category? = categoryRepository.findById(id).orElse(null)

    fun createCategory(category: Category): Category = categoryRepository.save(category)

    fun updateCategory(id: Long, category: Category): Category? {
        return if (categoryRepository.existsById(id)) {
            categoryRepository.save(category.copy(id = id))
        } else null
    }

    fun deleteCategory(id: Long) = categoryRepository.deleteById(id)
}
