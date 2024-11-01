package com.sabaka.journal.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name = "speakers")
data class Speaker(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @field:NotBlank(message = "Speaker name is required")
    @Column(nullable = false)
    val name: String = "",

    @field:NotBlank(message = "Speaker bio is required")
    @Column(nullable = false)
    val bio: String = "",

    @ManyToMany(mappedBy = "speakers")
    @JsonIgnore // Избегаем циклических ссылок
    val events: List<Event> = mutableListOf()
)
