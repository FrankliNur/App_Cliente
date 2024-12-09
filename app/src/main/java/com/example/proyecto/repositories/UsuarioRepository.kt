package com.example.proyecto.repositories

import com.example.proyecto.api.JSONPlaceHolderService
import com.example.proyecto.models.Restaurant
import com.example.proyecto.models.Token
import com.example.proyecto.models.Usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object UsuarioRepository {
    fun registro(
        usuario: Usuario,
        onSuccess: (Usuario) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val service = retrofit.create(JSONPlaceHolderService::class.java)
        service.registro(usuario).enqueue(object : Callback<Usuario> {
            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                if (response.isSuccessful) {
                    val registroUsuario = response.body()
                    onSuccess(registroUsuario!!)
                }
            }

            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                println("Error: ${t.message}")
                onError(t)
            }
        })
    }

    fun login(
        email: String,
        password: String,
        onSuccess: (String) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val service = retrofit.create(JSONPlaceHolderService::class.java)
        val credentials = mapOf("email" to email, "password" to password)

        service.login(credentials).enqueue(object : Callback<Token> {
            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                if (response.isSuccessful) {
                    val token = response.body()
                    onSuccess(token!!.access_token)
                }
            }

            override fun onFailure(call: Call<Token>, t: Throwable) {
                println("Error: ${t.message}")
                onError(t)
            }
        })
    }
    //Restaurante

    fun getRestaurants(
        token: String,
        onSuccess: (List<Restaurant>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val service = retrofit.create(JSONPlaceHolderService::class.java)
        service.getRestaurants("Bearer $token").enqueue(object : Callback<List<Restaurant>> {
            override fun onResponse(call: Call<List<Restaurant>>, response: Response<List<Restaurant>>) {
                if (response.isSuccessful) {
                    val restaurants = response.body()
                    onSuccess(restaurants ?: emptyList())
                }
            }

            override fun onFailure(call: Call<List<Restaurant>>, t: Throwable) {
                onError(t)
            }
        })
    }

}