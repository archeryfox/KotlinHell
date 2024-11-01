package com.sabaka.journal.repository

import com.sabaka.journal.model.Ticket
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface TicketRepository : JpaRepository<Ticket, Long> {
    fun findByPrice(price: Double, pageable: Pageable): Page<Ticket>
}
