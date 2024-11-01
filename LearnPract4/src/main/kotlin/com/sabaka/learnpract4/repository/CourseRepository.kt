package com.sabaka.learnpract4.repository

import com.sabaka.learnpract4.controller.CourseModel
import org.springframework.data.jpa.repository.JpaRepository


interface CourseRepository : JpaRepository<CourseModel, Int>
