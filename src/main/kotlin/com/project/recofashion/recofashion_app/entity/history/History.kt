package com.project.recofashion.recofashion_app.entity.history

import com.project.recofashion.recofashion_app.entity.user.Color
import java.time.LocalDate
import javax.persistence.*

@Entity
data class History(
        @Id @GeneratedValue
        var id: Long? = null,
        var username: String,

        @ManyToOne(targetEntity = Color::class)
        var topColor: Color,

        @ManyToOne(targetEntity = Color::class)
        var pantsColor: Color,
        var date: LocalDate
)