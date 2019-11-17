package com.project.recofashion.recofashion_app.service

import com.project.recofashion.recofashion_app.entity.user.Color

interface ColorService {
    fun getMonoColor(color: Color): Color

    fun getVividColor(color: Color): Color

    fun getPastelColor(color: Color): Color

    fun getDeepColor(color: Color): Color

    fun getTonInTon(color: Color): List<Color>

    fun getTonOnTon(color: Color): List<Color>
}