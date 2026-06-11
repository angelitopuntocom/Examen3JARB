package com.example.examen3.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.examen3.model.Jugador

@Dao
interface JugadorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarJugador(jugador: Jugador)
}