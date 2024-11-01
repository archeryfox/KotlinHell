package com.sabaka.journal.repository

import com.sabaka.journal.model.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : JpaRepository<Category, Long> {
    // Можно добавить кастомные методы для поиска, если понадобится
}
    