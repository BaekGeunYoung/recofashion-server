package com.project.recofashion.recofashion_app.entity.user

import javax.persistence.Entity

@Entity
data class User(
        var id: Long? = null,
        var username: String,
        var password: String,
        var firstName: String,
        var lastName: String,
        var roles: MutableSet<Role>
)