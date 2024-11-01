package com.sabaka.journal.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

@Entity
@Table(name = "events")
data class Event(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @field:NotBlank(message = "Event name is required")
    @Column(nullable = false)
    val name: String = "",

    @field:NotBlank(message = "Event description is required")
    @Column(nullable = false)
    val description: String = "",

    @field:NotNull(message = "Event date is required")
    val date: LocalDateTime? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venue_id", nullable = false)
    val venue: Venue? = null,

    @OneToMany(mappedBy = "event", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val tickets: List<Ticket>? = mutableListOf(),

    @ManyToMany
    @JoinTable(
        name = "event_speakers",
        joinColumns = [JoinColumn(name = "event_id")],
        inverseJoinColumns = [JoinColumn(name = "speaker_id")]
    )
    val speakers: List<Speaker>? = mutableListOf(),

    // Добавление связи с категорией
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    val category: Category? = null
)