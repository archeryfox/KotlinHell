package com.sabaka.learnpract4.controller

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank

@Entity
data class CourseModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @NotBlank(message = "Название курса не должно быть пустым")
    val name: String = "",

    @NotBlank(message = "Описание курса не должно быть пустым")
    val description: String = "",

    // Связь с сущностью Student
    @ManyToOne
    @JoinColumn(name = "student_id")
    val student: StudentModel? = null
)
