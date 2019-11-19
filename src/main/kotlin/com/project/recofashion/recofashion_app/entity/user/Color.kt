package com.project.recofashion.recofashion_app.entity.user

import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.IdClass

@Entity @IdClass(Color::class)
data class Color(@Id val r: Int, @Id val g: Int, @Id val b: Int) : Serializable{
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