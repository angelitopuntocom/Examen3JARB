package com.example.examen3.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.examen3.model.Jugador

@Dao
interface JugadorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarJugador(jugador: Jugador)

    // Nueva función para buscar un jugador por sus credenciales
    @Query("SELECT * FROM tabla_jugadores WHERE nombre = :nombre AND contrasena = :contrasena LIMIT 1")
    suspend fun verificarCredenciales(nombre: String, contrasena: String): Jugador?
}