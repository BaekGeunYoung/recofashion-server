package com.project.recofashion.recofashion_app.entity.user

import javax.persistence.*

@Entity
data class User(
        @Id @GeneratedValue
        var id: Long? = null,

        @Column(name="username", unique = true, length = 200)
        var username: String,
        var password: String,
        var firstName: String,
        var lastName: String,

        @Enumerated(EnumType.STRING)
        @ElementCollection(fetch = FetchType.EAGER)
        var roles: MutableSet<Role>,
        var skin: Skin,

        @ElementCollection(fetch = FetchType.EAGER)
        var favoriteColors: MutableSet<Color>? = null
)