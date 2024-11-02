package com.sabaka.journal.repository

import com.sabaka.journal.model.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : JpaRepository<Order, Long> {
    // Добавьте методы поиска при необходимости, например, findByStatus
    fun findByStatus(status: String): List<Order>
}
