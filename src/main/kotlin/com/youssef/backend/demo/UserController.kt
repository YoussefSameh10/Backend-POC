package com.youssef.backend.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(@Autowired val userService: UserService) {
    @GetMapping("/users")
    fun getAllUsers(): Collection<User> = userService.findAllUsers()

    @GetMapping("/user")
    fun getUser(@RequestParam id: Long): User? = userService.findUserByID(id)

    @PostMapping("/user")
    fun createUser(@RequestBody user: User): ResponseEntity<User> = ResponseEntity(userService.createUser(user), HttpStatus.CREATED)

    @PutMapping("/user/{id}")
    fun updateUser(
        @PathVariable id: Long,
        @RequestBody updatedUser: User
    ): ResponseEntity<User> {
        val user = userService.findUserByID(id) ?: return ResponseEntity(HttpStatus.NOT_FOUND)

        user.name = updatedUser.name
        user.email = updatedUser.email

        val savedUser = userService.createUser(user)
        return ResponseEntity.ok(savedUser)
    }

    @DeleteMapping("/user/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Void> {
        userService.deleteUser(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}