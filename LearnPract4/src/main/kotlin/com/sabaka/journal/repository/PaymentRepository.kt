package com.sabaka.journal.repository

import com.sabaka.journal.model.Payment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PaymentRepository : JpaRepository<Payment, Long> {
    // Добавьте методы поиска при необходимости, например, findByOrderId
    fun findByOrderId(orderId: Long): List<Payment>
}
