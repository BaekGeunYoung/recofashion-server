package com.project.recofashion.recofashion_app.entity.history

import com.project.recofashion.recofashion_app.entity.user.Color
import com.project.recofashion.recofashion_app.entity.user.User
import java.time.LocalDate

data class History(
        var id: Long? = null,
        var user: User,
        var topColor: Color,
        var pantsColor: Color,
        var date: LocalDate
)