package com.example.proyecto.ui.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.databinding.ItemRestaurantBinding
import com.example.proyecto.models.Restaurant
import com.example.proyecto.ui.activities.RestaurantDetailActivity

class RestaurantAdapter(private val restaurants: List<Restaurant>) :
    RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val binding =
            ItemRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RestaurantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = restaurants[position]
        holder.bind(restaurant)
    }

    override fun getItemCount(): Int = restaurants.size

    //    inner class RestaurantViewHolder(private val binding: ItemRestaurantBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//
//        fun bind(restaurant: Restaurant) {
//            binding.nameTextView.text = restaurant.name
//            binding.addressTextView.text = restaurant.address
//        }
//    }
    inner class RestaurantViewHolder(private val binding: ItemRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(restaurant: Restaurant) {
            binding.nameTextView.text = restaurant.name
            binding.addressTextView.text = restaurant.address

            // Listener de clic en el restaurante
            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, RestaurantDetailActivity::class.java)
                intent.putExtra("restaurantId", restaurant.id)  // Asegúrate de que el id es un Int
                Log.d("RestaurantListActivity", "Restaurant ID passed: ${restaurant.id}")
                binding.root.context.startActivity(intent)
            }


        }
    }

}
