package com.sabaka.journal.model

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "orders")
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id", nullable = false)
    val ticket: Ticket? = null,

    val orderDate: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    val status: String = "" // Например: "PENDING", "COMPLETED", "CANCELLED"
)
