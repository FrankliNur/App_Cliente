package com.example.proyecto.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.proyecto.models.Usuario
import com.example.proyecto.repositories.UsuarioRepository

class RegistroViewModel : ViewModel() {
    fun registro(usuario: Usuario, onSuccess: () -> Unit, onError: (Throwable) -> Unit) {
        UsuarioRepository.registro(usuario,
            onSuccess = {
                onSuccess()
            }, onError = {
                onError(it)
            })

    }
}
