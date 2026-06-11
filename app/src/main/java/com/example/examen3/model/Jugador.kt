package com.example.examen3.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_jugadores")
data class Jugador(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val contrasena: String
)