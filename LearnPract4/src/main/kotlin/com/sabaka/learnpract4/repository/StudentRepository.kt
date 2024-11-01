package com.sabaka.learnpract4.repository

import com.sabaka.learnpract4.controller.StudentModel
import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository: JpaRepository<StudentModel, Int> {
}