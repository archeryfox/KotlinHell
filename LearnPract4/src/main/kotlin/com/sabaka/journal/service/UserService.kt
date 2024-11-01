package com.sabaka.journal.service

import com.sabaka.journal.model.User
import com.sabaka.journal.repository.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun getAllUsers(pageable: Pageable): Page<User> = userRepository.findAll(pageable)

    fun getUserById(id: Long): User =
        userRepository.findById(id).orElseThrow { RuntimeException("User not found") }

    fun createUser(user: User): User = userRepository.save(user)

    fun updateUser(id: Long, updatedUser: User): User {
        val user = getUserById(id)
        return userRepository.save(user.copy(name = updatedUser.name, email = updatedUser.email))
    }

    fun deleteUser(id: Long) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id)
        } else {
            throw RuntimeException("User not found")
        }
    }

    fun searchUsers(name: String, pageable: Pageable): Page<User> =
        userRepository.findByNameContainingIgnoreCase(name, pageable)
}
