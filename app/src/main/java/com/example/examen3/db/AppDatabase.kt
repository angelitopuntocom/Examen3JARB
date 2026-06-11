package com.example.examen3.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.examen3.converters.Converters
import com.example.examen3.dao.UsuarioDao
import com.example.examen3.dao.JugadorDao // 1. Importación del nuevo DAO
import com.example.examen3.model.Usuario
import com.example.examen3.model.Jugador // 2. Importación del nuevo Modelo

// 3. Se agrega Jugador::class a las entidades y se incrementa la versión a 2
@Database(entities = [Usuario::class, Jugador::class], version = 2)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao

    abstract fun jugadorDao(): JugadorDao // 4. Función abstracta para el nuevo DAO
}