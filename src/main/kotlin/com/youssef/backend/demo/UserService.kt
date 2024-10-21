package com.youssef.backend.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserService(private val userRepo: UserRepository) {
    fun findAllUsers(): List<User> = userRepo.findAll()
    fun findUserByID(id: Long): User? = userRepo.findByIdOrNull(id)
    fun createUser(user: User): User = userRepo.save(user)
    fun deleteUser(id: Long) = userRepo.deleteById(id)
}