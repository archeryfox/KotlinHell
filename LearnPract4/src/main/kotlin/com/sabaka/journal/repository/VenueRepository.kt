package com.sabaka.journal.repository

import com.sabaka.journal.model.Venue
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface VenueRepository : JpaRepository<Venue, Long> {
    fun findByNameContainingIgnoreCase(name: String, pageable: Pageable): Page<Venue>
}
