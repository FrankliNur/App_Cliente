package com.example.proyecto.ui.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto.api.JSONPlaceHolderService
import com.example.proyecto.databinding.ActivityRestaurantDetailBinding
import com.example.proyecto.models.Restaurant
import com.example.proyecto.repositories.RetrofitRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.bumptech.glide.Glide


class RestaurantDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRestaurantDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val restaurantId = intent.getIntExtra("restaurantId", -1)
        Log.d("RestaurantDetailActivity", "Restaurant ID received: $restaurantId")
        if (restaurantId != -1) {
            fetchRestaurantDetails(restaurantId)
        } else {
            Toast.makeText(this, "Error: no se encontró el ID del restaurante", Toast.LENGTH_SHORT).show()
        }


    }

    private fun fetchRestaurantDetails(restaurantId: Int) {
        // Obtener el token de SharedPreferences
        val sharedPreferences = getSharedPreferences("usuario", MODE_PRIVATE)
        val token = sharedPreferences.getString("token", null)

        if (token != null) {
            val retrofit = RetrofitRepository.getRetrofitInstance()
            val service = retrofit.create(JSONPlaceHolderService::class.java)

            // Llamada a la API para obtener los detalles del restaurante
            service.getRestauranteById("Bearer $token", restaurantId).enqueue(object : Callback<Restaurant> {
                override fun onResponse(call: Call<Restaurant>, response: Response<Restaurant>) {
                    if (response.isSuccessful) {
                        val restaurant = response.body()
                        if (restaurant != null) {
                            // Mostrar los detalles del restaurante en la interfaz de usuario
                            Log.d("RestaurantDetailActivity", "Detalles del restaurante recibidos: $restaurant")
                            binding.nameTextView.text = restaurant.name
                            binding.addressTextView.text = restaurant.address
                            binding.latitudeTextView.text = restaurant.latitude.toString()
                            binding.longitudeTextView.text = restaurant.longitude.toString()

                            // Cargar el logo del restaurante usando Glide
                            Glide.with(this@RestaurantDetailActivity)
                                .load(restaurant.logo)
                                .into(binding.logoImageView)
                        }
                    } else {
                        Toast.makeText(this@RestaurantDetailActivity, "Error al obtener los detalles: ${response.message()}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Restaurant>, t: Throwable) {
                    Toast.makeText(this@RestaurantDetailActivity, "Error en la conexión: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            Toast.makeText(this, "Token no disponible", Toast.LENGTH_SHORT).show()
        }
    }


}
