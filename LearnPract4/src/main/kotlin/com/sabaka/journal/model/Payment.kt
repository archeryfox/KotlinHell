package com.sabaka.journal.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "payments")
data class Payment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    val order: Order? = null,

    @Column(nullable = false)
    val amount: Double = 0.0,

    @Column(nullable = false)
    val paymentMethod: String = "", // Например: "CREDIT_CARD", "PAYPAL"

    val paymentDate: LocalDateTime = LocalDateTime.now()
)
