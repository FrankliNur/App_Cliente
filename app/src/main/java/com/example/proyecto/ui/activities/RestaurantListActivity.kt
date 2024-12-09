package com.example.proyecto.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyecto.databinding.ActivityRestaurantListBinding
import com.example.proyecto.models.Restaurant
import com.example.proyecto.repositories.UsuarioRepository
import com.example.proyecto.ui.adapters.RestaurantAdapter

class RestaurantListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRestaurantListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("usuario", MODE_PRIVATE)
        val token = sharedPreferences.getString("token", null)

        if (token != null) {
            UsuarioRepository.getRestaurants(
                token,
                onSuccess = { restaurants ->
                    setupRecyclerView(restaurants)
                },
                onError = { error ->
                    Toast.makeText(this, "Error al obtener restaurantes: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            )
        } else {
            Toast.makeText(this, "No se encontró el token", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupRecyclerView(restaurants: List<Restaurant>) {
        val adapter = RestaurantAdapter(restaurants)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }
}
