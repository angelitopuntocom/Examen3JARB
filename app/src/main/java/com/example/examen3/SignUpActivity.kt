package com.example.examen3

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.examen3.model.Jugador
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        val etUsuario = findViewById<EditText>(R.id.etRegistroUsuario)
                val etContrasena = findViewById<EditText>(R.id.etRegistroContrasena)
                val btnRegistrarse = findViewById<Button>(R.id.btnConfirmarRegistro)

                // Suponiendo que obtienes la instancia de tu BD (ajusta según tu implementación)
                // Ejemplo común: val db = AppDatabase.getInstance(this)
                // O si la inicializaste en MyApplication:
                // val db = (application as MyApplication).database

                btnRegistrarse.setOnClickListener {
            val nombre = etUsuario.text.toString().trim()
            val contrasena = etContrasena.text.toString().trim()

            // Validación básica de campos vacíos
            if (nombre.isEmpty() || contrasena.isEmpty()) {
                Toast.makeText(this, "Por favor, llena todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Crear el objeto Jugador
            val nuevoJugador = Jugador(nombre = nombre, contrasena = contrasena)

            // Insertar en la base de datos usando corrutinas en un hilo secundario
            lifecycleScope.launch(Dispatchers.IO) {
                // db.jugadorDao().insertarJugador(nuevoJugador)

                // Regresar al hilo principal para mostrar confirmación visual
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@SignUpActivity, "¡Jugador registrado con éxito!", Toast.LENGTH_SHORT).show()
                    finish() // Cierra la actividad y regresa a la pantalla anterior
                }
            }
        }
    }
}