package com.sabaka.journal.controller

import com.sabaka.journal.model.User
import com.sabaka.journal.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = ["http://localhost:5173"])
class AuthController(@Autowired private val userService: UserService) {

    @PostMapping("/register")
    fun register(@RequestBody user: User): ResponseEntity<String> {
        return try {
            userService.registerUser(user)
            ResponseEntity.ok("User registered successfully")
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    @PostMapping("/login")
    fun login(@RequestBody user: User): ResponseEntity<String> {
        return if (userService.authenticateUser(user.username, user.password)) {
            ResponseEntity.ok("User authenticated")
        } else {
            ResponseEntity.status(401).body("Invalid credentials")
        }
    }
}
