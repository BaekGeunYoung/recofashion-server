package com.project.recofashion.recofashion_app.entity.history

import com.project.recofashion.recofashion_app.entity.user.Color
import java.time.LocalDate

data class History(
        var id: Long? = null,
        var username: String,
        var topColor: Color,
        var pantsColor: Color,
        var date: LocalDate
)