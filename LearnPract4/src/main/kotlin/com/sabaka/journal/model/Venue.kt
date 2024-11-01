package com.sabaka.journal.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name = "venues")
data class Venue(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @field:NotBlank(message = "Venue name is required")
    @Column(nullable = false)
    val name: String = "",

    @field:NotBlank(message = "Location is required")
    @Column(nullable = false)
    val location: String = "",

    @OneToMany(mappedBy = "venue")
    @JsonIgnore // Исключаем бесконечные ссылки
    val events: List<Event> = mutableListOf()
)
