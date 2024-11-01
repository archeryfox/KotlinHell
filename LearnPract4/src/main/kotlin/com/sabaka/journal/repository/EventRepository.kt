package com.sabaka.journal.repository

import com.sabaka.journal.model.Event
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface EventRepository : JpaRepository<Event, Long> {
    fun findByNameContainingIgnoreCase(name: String, pageable: Pageable): Page<Event>
}
