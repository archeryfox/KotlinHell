package com.sabaka.journal.controller

import com.sabaka.journal.model.User
import com.sabaka.journal.service.UserService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = ["http://localhost:5173"])
class UserController(private val userService: UserService) {

    @GetMapping
    fun getAllUsers(pageable: Pageable): ResponseEntity<Page<User>> {
        return ResponseEntity.ok(userService.getAllUsers(pageable))
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<User> {
        return ResponseEntity.ok(userService.getUserById(id))
    }

    @PostMapping
    fun createUser(@RequestBody user: User): ResponseEntity<User> {
        return ResponseEntity(userService.createUser(user), HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody user: User): ResponseEntity<User> {
        return ResponseEntity.ok(userService.updateUser(id, user))
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Void> {
        userService.deleteUser(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/search")
    fun searchUsers(@RequestParam(name = "name") name: String, pageable: Pageable): ResponseEntity<Page<User>> {
        return ResponseEntity.ok(userService.searchUsers(name, pageable))
    }
}
