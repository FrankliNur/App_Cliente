package com.example.proyecto.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyecto.R
import com.example.proyecto.databinding.ActivityMainBinding
import com.example.proyecto.databinding.ActivityRegistroBinding
import com.example.proyecto.repositories.UsuarioRepository

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupEventListeners()
    }

    private fun setupEventListeners() {
        binding.registerButton.setOnClickListener {
            startActivity(RegistroActivity.newIntent(this))
        }
        binding.loginButton.setOnClickListener {
            val email = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()
            if (email.isBlank() || password.isBlank()) {
                Toast.makeText(
                    this,
                    "Por favor, ingrese su correo y contraseña.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            UsuarioRepository.login(email, password,
                onSuccess = { token ->
                    Toast.makeText(this, "Inicio de sesión exitoso.", Toast.LENGTH_SHORT).show()
                    val sharedPreferences = getSharedPreferences("usuario", MODE_PRIVATE)
                    sharedPreferences.edit().putString("token", token).apply()
                    startActivity(Intent(this, RestaurantListActivity::class.java))
                    finish()  // Cierra MainActivity si ya no es necesario
                },
                onError = {
                    Toast.makeText(this, "Error al iniciar sesión.", Toast.LENGTH_SHORT).show()
                })

        }
    }
}