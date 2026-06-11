package com.example.examen3

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        // Manejo de los márgenes del sistema (coincide con tu ID @+id/main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. Vincular el TextView donde se mostrará la información
        val tvUltimaConexion = findViewById<TextView>(R.id.tvUltimaConexion)

        // 2. Abrir o crear el espacio de almacenamiento temporal de SharedPreferences
        val sharedPreferences = getSharedPreferences("PreferenciaConexion", Context.MODE_PRIVATE)

        // 3. RECUPERAR: Leer el valor guardado anteriormente.
        // Si es la primera vez que se abre la app y no hay nada, usará el texto por defecto.
        val ultimaFecha = sharedPreferences.getString("ultima_fecha_hora", "No registrada (Primera conexión)")

        // Mostrar en la interfaz el valor recuperado
        tvUltimaConexion.text = "Ultima conexión: $ultimaFecha"

        // 4. ACTUALIZAR: Obtener la fecha y hora del sistema en este preciso momento
        val formateador = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        val fechaActual = formateador.format(Date())

        // Guardar la nueva fecha actual para que sea la "última" en la siguiente ocasión
        val editor = sharedPreferences.edit()
        editor.putString("ultima_fecha_hora", fechaActual)
        editor.apply() // Guarda los cambios en segundo plano de forma segura
    }
}