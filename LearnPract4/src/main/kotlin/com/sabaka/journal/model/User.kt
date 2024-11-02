package com.sabaka.journal.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @field:NotBlank(message = "User name is required")
    @Column(nullable = false)
    val username: String = "",

    @field:NotBlank(message = "Email is required")
    @Column(nullable = false, unique = true)
    val email: String = "",

    @field:NotBlank(message = "Password is required")
    @Column(nullable = false)
    val password: String = "",

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JsonIgnore // Исключаем бесконечные циклы
    val tickets: List<Ticket> = mutableListOf()
)
