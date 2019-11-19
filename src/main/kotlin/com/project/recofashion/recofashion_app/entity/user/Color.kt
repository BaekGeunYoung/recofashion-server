package com.project.recofashion.recofashion_app.entity.user

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Color(@Id @GeneratedValue val id: Long? = null, val r: Int, val g: Int, val b: Int) {
    fun mainColor() : String {
        if(r > g && r > b) {
            if( r >= 2*g - 1 && r <= 2*g + 1) return "ORANGE"
            return "RED"
        }
        else if(b > r && b > g) return "BLUE"
        else if(g > b && g > r) return "GREEN"
        else if(r == b) return "PURPLE"
        else if(r == 255 && b == 255 && g == 255) return "WHITE"
        else if(r == 0 && b == 0 && g == 0) return "BLACK"
        else return "NOT FOUND"
    }
}