package com.sabaka.learnpract4.service

import com.sabaka.learnpract4.controller.StudentModel

interface IStudentService {
    fun findAll(
        filters: Map<String, Any?>,
        page: Int,
        pageSize: Int
    ): List<StudentModel?>
    fun findAll(): List<StudentModel?>
    fun findByParams(params: Map<String, Any?>): List<StudentModel?>

    fun add(student: StudentModel?): StudentModel?

    fun update(student: StudentModel?): StudentModel?

    // Логическое удаление студента
    fun softDelete(id: Int)

    // Физическое удаление студента
    fun delete(id: Int)

    // Множественное удаление студентов по списку id
    fun bulkDelete(ids: List<Int>)
}
