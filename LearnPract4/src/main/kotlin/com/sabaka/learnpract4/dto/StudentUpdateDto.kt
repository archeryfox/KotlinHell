package com.sabaka.learnpract4.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class StudentUpdateDto(
    @get:NotBlank(message = "Имя не должно быть пустым")
    @get:Size(min = 2, max = 50, message = "Имя должно содержать от 2 до 50 символов")
    var name: String = "",
    @get:NotBlank(message = "Фамилия не должна быть пустой")
    var lastName: String = "",
    var firstName: String = "",
    var middleName: String = "",
    var course: Int = 0,
)
