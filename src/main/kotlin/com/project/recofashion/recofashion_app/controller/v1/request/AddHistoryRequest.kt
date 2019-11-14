package com.project.recofashion.recofashion_app.controller.v1.request

import com.project.recofashion.recofashion_app.entity.user.Color

data class AddHistoryRequest(
        var topColor: Color,
        var pantscolor: Color
)