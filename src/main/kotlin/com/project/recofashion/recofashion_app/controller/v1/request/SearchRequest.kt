package com.project.recofashion.recofashion_app.controller.v1.request

import com.project.recofashion.recofashion_app.entity.user.Color

data class SearchRequest(
        var color: Color,
        var temperature: Int
)