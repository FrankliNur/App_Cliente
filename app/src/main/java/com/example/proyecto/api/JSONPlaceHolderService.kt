package com.example.proyecto.api

import com.example.proyecto.models.Restaurant
import com.example.proyecto.models.Token
import com.example.proyecto.models.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface JSONPlaceHolderService {
    @POST("users")
    fun registro(@Body usuario: Usuario): Call<Usuario>

    @POST("users/login")
    fun login(@Body credentials: Map<String, String>): Call<Token>

    //Restaurants
    @GET("restaurants") // Asegúrate de que la URL de la API sea correcta
    fun getRestaurants(@Header("Authorization") token: String): Call<List<Restaurant>>

    @GET("restaurants/{id}")
    fun getRestauranteById(
        @Header("Authorization") token: String,  // Asegúrate de pasar el token de autorización
        @Path("id") id: Int                      // Asegúrate de pasar el id del restaurante
    ): Call<Restaurant>
}

