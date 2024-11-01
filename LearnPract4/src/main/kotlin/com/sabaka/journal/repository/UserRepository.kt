package com.sabaka.journal.repository

import com.sabaka.journal.model.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByNameContainingIgnoreCase(name: String, pageable: Pageable): Page<User>
}