package com.example.examen3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.examen3.db.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Manejo de los márgenes del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. Vincular los componentes de la interfaz
        val etUsuario = findViewById<EditText>(R.id.etNombreUsuario)
        val etContrasena = findViewById<EditText>(R.id.etContrasena)
        val btnIniciarSesion = findViewById<Button>(R.id.btnIniciarSesion)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)

        // 2. Instanciar la base de datos de Room
        // Nota: Asegúrate de usar el mismo nombre de base de datos que usas en el resto de tu app ("examen_db" es un ejemplo)
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "examen_db"
        ).build()

        // 3. Lógica de validación para Iniciar Sesión
        btnIniciarSesion.setOnClickListener {
            val nombre = etUsuario.text.toString().trim()
            val contrasena = etContrasena.text.toString().trim()

            // Validar que los campos no estén vacíos
            if (nombre.isEmpty() || contrasena.isEmpty()) {
                Toast.makeText(this, "Por favor, ingresa usuario y contraseña", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Consultar la BD en un hilo secundario
            lifecycleScope.launch(Dispatchers.IO) {
                val jugador = db.jugadorDao().verificarCredenciales(nombre, contrasena)

                // Regresar al hilo principal para mostrar mensajes o cambiar de pantalla
                withContext(Dispatchers.Main) {
                    if (jugador != null) {
                        // Las credenciales son correctas
                        val intent = Intent(this@MainActivity, ProfileActivity::class.java)
                        startActivity(intent)
                        finish() // Cierra el login para que no se pueda regresar con el botón "Atrás"
                    } else {
                        // Las credenciales NO existen o son incorrectas
                        Toast.makeText(this@MainActivity, "Las credenciales no son correctas", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        // 4. Lógica para ir a la pantalla de Registro
        btnRegistrar.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}