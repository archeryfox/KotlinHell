package com.sabaka.journal.repository

import com.sabaka.journal.model.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : JpaRepository<Category, Long> {
    // Добавьте методы поиска при необходимости, например, findByName
    fun findByName(name: String): Category?
}
