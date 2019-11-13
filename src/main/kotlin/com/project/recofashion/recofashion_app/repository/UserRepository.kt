package com.project.recofashion.recofashion_app.repository

import com.project.recofashion.recofashion_app.entity.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String): User?
}