package com.sabaka.learnpract4.controller

import jakarta.persistence.*
import jakarta.validation.constraints.Size

@Entity
data class StudentModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,

    @Size(min = 2, max = 50, message = "Имя должно содержать от 2 до 50 символов")
    var name: String = "",

    var lastName: String = "",

    var firstName: String = "",
    var middleName: String = "",

    var isDeleted: Boolean = false,

    @ManyToOne(fetch = FetchType.LAZY)
    var courseId: CourseModel = CourseModel() // Используем список для курсов
) {
    fun getCourseNames(): String {
        return this?.courseId?.name.toString()
    }
}
