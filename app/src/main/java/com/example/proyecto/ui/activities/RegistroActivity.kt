package com.example.proyecto.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyecto.R
import com.example.proyecto.databinding.ActivityRegistroBinding
import com.example.proyecto.models.Usuario
import com.example.proyecto.ui.viewmodel.RegistroViewModel

class
RegistroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistroBinding
    private val viewModel: RegistroViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegistroBinding.inflate(layoutInflater)
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
            val usuario = Usuario(
                name = binding.nameInput.text.toString(),
                email = binding.emailInput.text.toString(),
                password = binding.passwordInput.text.toString(),
                role = 1
            )
            viewModel.registro(usuario, onSuccess = {
                Toast.makeText(this, "Usuario registrado con éxito.", Toast.LENGTH_SHORT).show()
                finish()
            }, onError = {
                Toast.makeText(this, "Error al crear un usuario.", Toast.LENGTH_SHORT).show()
            })
        }
    }

    companion object {
        fun newIntent(mainActivity: MainActivity): Intent {
            return Intent(mainActivity, RegistroActivity::class.java)
        }
    }
}