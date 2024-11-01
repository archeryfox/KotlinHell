package com.sabaka.journal.repository

import com.sabaka.journal.model.Speaker
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface SpeakerRepository : JpaRepository<Speaker, Long> {
    fun findByNameContainingIgnoreCase(name: String, pageable: Pageable): Page<Speaker>
}
